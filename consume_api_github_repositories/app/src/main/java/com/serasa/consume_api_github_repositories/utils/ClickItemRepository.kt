package com.serasa.consume_api_github_repositories.utils

import com.serasa.consume_api_github_repositories.model.ItemsGitHub

interface ClickItemRepository {

    fun onClickRepository(repo: ItemsGitHub)

    fun onClickUserDetail(repo: ItemsGitHub)
}