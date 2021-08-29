package com.serasa.consume_api_github_repositories.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("html_url")
    val pagePR: String,
    @SerializedName("title")
    val titlePR: String,
    @SerializedName("user")
    val userPR: UserPR,
    @SerializedName("body")
    val bodyPR: String,
    @SerializedName("created_at")
    val createDatePR: String
)

data class UserPR(
    @SerializedName("login")
    val usernamePR: String,
    @SerializedName("avatar_url")
    val userImagePR: String
)
