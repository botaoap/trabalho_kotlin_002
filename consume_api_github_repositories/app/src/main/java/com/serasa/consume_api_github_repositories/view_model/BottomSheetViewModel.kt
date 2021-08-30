package com.serasa.consume_api_github_repositories.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.serasa.consume_api_github_repositories.model.ItemsGitHub
import com.serasa.consume_api_github_repositories.model.UserDetail
import com.serasa.consume_api_github_repositories.repository.RepositoryGitHubRepository

class BottomSheetViewModel : ViewModel() {

    private val _userDetail = MutableLiveData<UserDetail>()
    var userDetail: LiveData<UserDetail> = _userDetail

    private val _error = MutableLiveData<String>()
    var error: LiveData<String> = _error

    protected val repository = RepositoryGitHubRepository()

    fun fetchUserDetail(username: String) {
        repository.fetchUserDetail(username) { user, error ->
            user?.let {
                _userDetail.value = user
            }
            error?.let {
                _error.value = error
            }

        }
    }
}