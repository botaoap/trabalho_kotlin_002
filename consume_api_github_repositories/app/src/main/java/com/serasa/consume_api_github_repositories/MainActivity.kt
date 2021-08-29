package com.serasa.consume_api_github_repositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.serasa.consume_api_github_repositories.utils.replaceView
import com.serasa.consume_api_github_repositories.view.MainFragment
import com.serasa.consume_api_github_repositories.view.SearchRepositoriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        replaceView(SearchRepositoriesFragment.newInstance())
    }
}