package com.serasa.consume_api_github_repositories.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serasa.consume_api_github_repositories.model.ItemsGitHub
import com.serasa.consume_api_github_repositories.repository.RepositoryGitHubRepository

class SearchRepositoriesViewModel : ViewModel() {

    private val _repository = MutableLiveData<List<ItemsGitHub>>()
    var repository: LiveData<List<ItemsGitHub>> = _repository

    private val _error = MutableLiveData<String>()
    var error: LiveData<String> = _error

    private val repositoryGitHub = RepositoryGitHubRepository()
//    q: Char, language: String, sort: String, order: String
    fun getSearchRepository() {
//        q, language, sort, order
        repositoryGitHub.fetchAllRepositories() { listRepo, error ->
            listRepo?.apply {
                _repository.value = this.items
            }
            error?.apply {
                _error.value = error
            }
        }
    }
}