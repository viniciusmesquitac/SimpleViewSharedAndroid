package com.example.issuesapp.Service

import com.example.issuesapp.Model.Issue
import io.reactivex.Observable
import retrofit2.http.GET

interface INetworkAPI {

    @GET("issues")
    fun getAllIssues():Observable<List<Issue>>
}