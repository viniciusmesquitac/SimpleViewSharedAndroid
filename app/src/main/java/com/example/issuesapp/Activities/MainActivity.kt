package com.example.issuesapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.issuesapp.Service.INetworkAPI
import com.example.issuesapp.Model.Issue
import com.example.issuesapp.Adapters.IssueAdapter
import com.example.issuesapp.R
import com.example.issuesapp.Service.IssueListContract
import com.example.issuesapp.Service.RequestIssuePresenter
import com.example.issuesapp.View.IMainView
import com.example.issuesapp.View.OnItemClickListener
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.issue_item.view.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// how to observer lists works?

// what is ConverterFactory, AdapterFactory and RxJava2 ?

// why working on AndroidSchedulers main thread ?

// what is CompositeDisposable and what this do?

class MainActivity : AppCompatActivity(), IMainView {

    private lateinit var issues: ArrayList<Issue>
    private lateinit var adapter: IssueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchIssues("https://api.github.com/repos/JetBrains/kotlin/")


        val intent = Intent(this, InfoIssueActivity::class.java)
        recycler_view.addOnItemClickListener(object: OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                intent.putExtra("issue", issues[position])
                createPairParser(intent, view.image_view, view.txt_title)
            }
        })
    }

    private fun fetchIssues(baseUrl: String) {
        val composite = CompositeDisposable()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()


        val issuesAPI = retrofit?.create(INetworkAPI::class.java)
        val response = issuesAPI?.getAllIssues()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())

        composite.add(response?.subscribe(this::handleResponse))
    }

    private fun handleResponse(issueList: List<Issue>) {
        issues = ArrayList(issueList)
        adapter = IssueAdapter(issues)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun createPairParser(intent: Intent, image_view: View, txt_title: View) {
        val pairImageView = Pair.create<View, String>(image_view, "image_view_transition")
        val pairTextView = Pair.create<View, String>(txt_title, "text_title_transition")
        val pairs = ArrayList<Pair<View, String>>()
        pairs.add(pairImageView)
        pairs.add(pairTextView)
        val pairArray: Array<Pair<View,String>> = pairs.toTypedArray()
        val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, *pairArray)
        startActivity(Intent(intent), option.toBundle())
    }

    override fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {

        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

}
