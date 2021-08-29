package com.serasa.consume_api_github_repositories.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serasa.consume_api_github_repositories.model.PullRequest
import com.serasa.consume_api_github_repositories.repository.RepositoryGitHubRepository

class PullRequestViewModel : ViewModel() {

    private val _pullRequest = MutableLiveData<List<PullRequest>>()
    var pullRequest: LiveData<List<PullRequest>> = _pullRequest

    private val _error = MutableLiveData<String>()
    var error: LiveData<String> = _error

    private val repositoryGithub = RepositoryGitHubRepository()

    fun getPullRequest(owner: String, repo: String) {
        repositoryGithub.fetchPullRequest(owner, repo) { listPull, error ->
            listPull?.apply {
                _pullRequest.value = this
            }
            error?.apply {
                _error.value = error
            }
        }
    }
}