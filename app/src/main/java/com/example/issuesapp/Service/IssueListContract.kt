package com.example.issuesapp.Service

import com.example.issuesapp.Model.Issue

interface IssueListContract {


    interface Model {

    }

    interface View {
        fun setDataToRecyclerView(issueList: List<Issue>)
    }

    interface Presenter {
        fun requestDataFromAPI()
    }

}