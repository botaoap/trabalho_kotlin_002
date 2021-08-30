package com.serasa.consume_api_github_repositories.repository

import android.os.Handler
import android.os.Looper
import com.serasa.consume_api_github_repositories.model.ItemsGitHub
import com.serasa.consume_api_github_repositories.model.PullRequest
import com.serasa.consume_api_github_repositories.model.RepositoryGitHub
import com.serasa.consume_api_github_repositories.model.UserDetail
import com.serasa.consume_api_github_repositories.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryGitHubRepository {

    private val retrofit = RetrofitBuilder.getEndpointApiGitHub()
//    q: Char, language: String, sort: String, order: String,
    fun fetchAllRepositories(callBack: (RepositoryGitHub?, String?) -> Unit) {
//        q,language,sort, order

        Handler(Looper.getMainLooper()).postDelayed({
            retrofit.searchRepositories()
                .enqueue(object : Callback<RepositoryGitHub> {
                    override fun onResponse(
                        call: Call<RepositoryGitHub>,
                        response: Response<RepositoryGitHub>
                    ) {
                        if (response.body() != null) {
                            response.body()?.let {
                                callBack(it, null)
                            }
                        } else {
                            callBack(null, "Error not recognized!!")
                        }
                    }

                    override fun onFailure(call: Call<RepositoryGitHub>, t: Throwable) {
                        callBack(null, t.localizedMessage)
                    }

                })
        },2500)
    }

    fun fetchPullRequest(owner: String, repo: String,callBack: (List<PullRequest>?, String?) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            retrofit.getPullRequestRepository(owner, repo)
                .enqueue(object : Callback<List<PullRequest>> {
                    override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                        if (response.body() != null) {
                            response.body()?.let {
                                callBack(it, null)
                            }
                        } else {
                            callBack(null, "Error not recognized!!")
                        }
                    }

                    override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                        callBack(null, t.localizedMessage)
                    }

                })
        }, 2500)


    }

    fun fetchUserDetail(username: String, callBack: (UserDetail?, String?) -> Unit) {
        retrofit.getUserDetail(username)
            .enqueue(object : Callback<UserDetail> {
                override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                    if (response.body() != null) {
                        response.body()?.let {
                            callBack(it, null)
                        }
                    }else {
                        callBack(null, "Error not recognized!!")
                    }

                }

                override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                    callBack(null, t.localizedMessage)
                }

            })
    }
}
