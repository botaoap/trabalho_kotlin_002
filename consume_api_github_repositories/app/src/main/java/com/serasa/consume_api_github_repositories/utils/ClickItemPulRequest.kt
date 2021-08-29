package com.serasa.consume_api_github_repositories.utils

import com.serasa.consume_api_github_repositories.model.PullRequest

interface ClickItemPulRequest {

    fun onClickHTML(pageUrl: PullRequest)

    fun onClickPulRequestDetail(user: PullRequest)

}