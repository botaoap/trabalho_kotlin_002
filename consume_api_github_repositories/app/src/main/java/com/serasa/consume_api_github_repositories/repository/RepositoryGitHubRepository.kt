package com.serasa.consume_api_github_repositories.repository

import com.serasa.consume_api_github_repositories.model.PullRequest
import com.serasa.consume_api_github_repositories.model.RepositoryGitHub
import com.serasa.consume_api_github_repositories.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryGitHubRepository {

    private val retrofit = RetrofitBuilder.getEndpointApiGitHub()
//    q: Char, language: String, sort: String, order: String,
    fun fetchAllRepositories(callBack: (RepositoryGitHub?, String?) -> Unit): Boolean {
//        q,language,sort, order
        retrofit.searchRepositories()
            .enqueue(object : Callback<RepositoryGitHub>{
                override fun onResponse(
                    call: Call<RepositoryGitHub>,
                    response: Response<RepositoryGitHub>
                ) {
                    response.body()?.let {
                        callBack(it, null)
                    }
                    if (response.code() != 200) {
                        callBack(null, "Error not recognized!!")
                    }
                }

                override fun onFailure(call: Call<RepositoryGitHub>, t: Throwable) {
                    callBack(null, t.localizedMessage)
                }

            })
        return true
    }

    fun fetchPullRequest(owner: String, repo: String,callBack: (List<PullRequest>?, String?) -> Unit) {
        retrofit.getPullRequestRepository(owner, repo)
            .enqueue(object : Callback<List<PullRequest>> {
                override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                    response.body()?.let {
                        callBack(it, null)
                    }

                    if (response.code() != 200) {
                        callBack(null, "Error not recognized!!")
                    }
                }

                override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                    callBack(null, t.localizedMessage)
                }

            })
    }
}
