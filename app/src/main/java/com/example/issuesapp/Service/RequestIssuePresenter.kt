package com.example.issuesapp.Service

import com.example.issuesapp.Model.Issue
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RequestIssuePresenter: IssueListContract.Presenter, IssueListContract.View {

    lateinit var issueListView: List<Issue>

    override fun setDataToRecyclerView(issueList: List<Issue>) {
        issueListView = issueList
    }

    override fun requestDataFromAPI() {
        val composite = CompositeDisposable()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.github.com/repos/JetBrains/kotlin/")
            .build()


        val issuesAPI = retrofit?.create(INetworkAPI::class.java)
        val response = issuesAPI?.getAllIssues()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())

        composite.add(response?.subscribe(this::setDataToRecyclerView))
    }

}