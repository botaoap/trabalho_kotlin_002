package com.serasa.consume_api_github_repositories.service

import com.serasa.consume_api_github_repositories.enum.URLToGitHubApi
import com.serasa.consume_api_github_repositories.model.RepositoryGitHub
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun connectionToAPI() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(URLToGitHubApi.API_GITHUB.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getEndpointApiGitHub() : EndpointApiGitHub{
        return connectionToAPI().create(EndpointApiGitHub::class.java)
    }
}