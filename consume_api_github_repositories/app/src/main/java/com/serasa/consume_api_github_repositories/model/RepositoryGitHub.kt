package com.serasa.consume_api_github_repositories.model

import com.google.gson.annotations.SerializedName

data class RepositoryGitHub(
    @SerializedName("items")
    val items: List<ItemsGitHub>
)

data class ItemsGitHub(
    @SerializedName("name")
    val title: String,
    @SerializedName("owner")
    val owner: UserGitHub,
    @SerializedName("html_url")
    val page: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("pulls_url")
    val pullRequest: PullRequest,
    @SerializedName("stargazers_count")
    val startCount: Int,
    @SerializedName("forks_count")
    val forkCount: Int,
    @SerializedName("language")
    val language: String
)

data class UserGitHub(
    @SerializedName("login")
    val userName: String,
    @SerializedName("avatar_url")
    val userAvatar: String,
    @SerializedName("html_url")
    val profileUrlUser: String,
    @SerializedName("type")
    val typePower: String
)