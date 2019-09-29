package com.example.issuesapp.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Issue(
    @SerializedName("assignee")
    val assignee: @RawValue Any?=null,
    @SerializedName("assignees")
    val assignees: @RawValue List<Any>?=null,
    @SerializedName("author_association")
    val authorAssociation: String?=null,
    @SerializedName("body")
    val body: String?=null,
    @SerializedName("closed_at")
    val closedAt: @RawValue Any?=null,
    @SerializedName("comments")
    val comments: Int?=null,
    @SerializedName("comments_url")
    val commentsUrl: String?=null,
    @SerializedName("created_at")
    val createdAt: String?=null,
    @SerializedName("events_url")
    val eventsUrl: String?=null,
    @SerializedName("html_url")
    val htmlUrl: String?=null,
    @SerializedName("id")
    val id: Int?=null,
    @SerializedName("labels")
    val labels: @RawValue List<Any>?=null,
    @SerializedName("labels_url")
    val labelsUrl: String?=null,
    @SerializedName("locked")
    val locked: Boolean?=null,
    @SerializedName("milestone")
    val milestone: @RawValue Any?=null,
    @SerializedName("node_id")
    val nodeId: String?=null,
    @SerializedName("number")
    val number: Int?=null,
    @SerializedName("pull_request")
    val pullRequest: PullRequest?=null,
    @SerializedName("repository_url")
    val repositoryUrl: String?=null,
    @SerializedName("state")
    val state: String?=null,
    @SerializedName("title")
    val title: String?=null,
    @SerializedName("updated_at")
    val updatedAt: String?=null,
    @SerializedName("url")
    val url: String?=null,
    @SerializedName("user")
    val user: User?=null
): Parcelable

@Parcelize
data class PullRequest(
    @SerializedName("diff_url")
    val diffUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("patch_url")
    val patchUrl: String,
    @SerializedName("url")
    val url: String
): Parcelable

@Parcelize
data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
): Parcelable