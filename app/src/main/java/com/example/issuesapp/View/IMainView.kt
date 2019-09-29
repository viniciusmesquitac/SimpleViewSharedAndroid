package com.example.issuesapp.View

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.issuesapp.Activities.MainActivity

interface IMainView {
    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener)
}