package com.example.issuesapp.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import com.example.issuesapp.Model.Issue
import com.example.issuesapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info_issue.*
import kotlinx.android.synthetic.main.issue_item.*

class InfoIssueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_issue)


        val bundle: Bundle? = intent.extras
        var issue = Issue()

        bundle?.apply {
            issue = getParcelable("issue")!!
            text_title_info.text = issue.title
            Picasso.get()
                .load(issue.user?.avatarUrl)
                .into(image_info)

        }
    }
}
