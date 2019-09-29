package com.example.issuesapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.issuesapp.Model.Issue
import com.example.issuesapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info_issue.view.*
import kotlinx.android.synthetic.main.issue_item.view.*


class IssueAdapter(val issues: List<Issue>): RecyclerView.Adapter<IssueAdapter.IssueViewHolder> (){


    var onItemClick: ((Issue) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.issue_item, parent, false)
        return IssueViewHolder(inflate)
    }

    override fun getItemCount(): Int = issues.size

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(issues[position])
    }


    inner class IssueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(issue: Issue) {
            with(issue){
                itemView.txt_title.text = title
                itemView.txt_state.text = state
                Picasso.get()
                    .load(user?.avatarUrl)
                    .into(itemView.image_view)

            }
        }
    }
}