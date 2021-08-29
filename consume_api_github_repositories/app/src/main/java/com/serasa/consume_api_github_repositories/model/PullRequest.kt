package com.serasa.consume_api_github_repositories.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
) : Serializable

data class UserPR(
    @SerializedName("login")
    val usernamePR: String,
    @SerializedName("avatar_url")
    val userImagePR: String
) : Serializable
