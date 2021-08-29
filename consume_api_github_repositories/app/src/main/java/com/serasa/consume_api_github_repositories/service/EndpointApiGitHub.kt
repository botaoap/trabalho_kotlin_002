package com.serasa.consume_api_github_repositories.service

import com.serasa.consume_api_github_repositories.model.PullRequest
import com.serasa.consume_api_github_repositories.model.RepositoryGitHub
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointApiGitHub {

    @GET("/search/repositories?q=language:Java&sort=fork&order=desc")
    fun searchRepositories(
//        @Query("q") q: Char,
//        @Path ("language") language: String,
//        @Path ("sort") sort: String,
//        @Path ("order") order: String,
//        @Query ("per_page") per_page: String
    ) : Call<RepositoryGitHub>

    @GET("/repos/{owner}/{repo}/pulls")
    fun getPullRequestRepository(
        @Path ("owner") owner: String,
        @Path ("repo") repo: String,

    ) : Call<List<PullRequest>>
}