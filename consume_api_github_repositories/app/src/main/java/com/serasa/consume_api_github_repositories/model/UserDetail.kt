package com.serasa.consume_api_github_repositories.model

import com.google.gson.annotations.SerializedName

data class UserDetail(

    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("bio")
    val bio: String

)