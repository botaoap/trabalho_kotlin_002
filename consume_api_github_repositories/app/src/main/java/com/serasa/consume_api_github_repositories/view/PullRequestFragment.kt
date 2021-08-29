package com.serasa.consume_api_github_repositories.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.serasa.consume_api_github_repositories.R
import com.serasa.consume_api_github_repositories.adapter.AdapterPullRequestGitHub
import com.serasa.consume_api_github_repositories.databinding.PullRequestFragmentBinding
import com.serasa.consume_api_github_repositories.model.PullRequest
import com.serasa.consume_api_github_repositories.utils.replaceView
import com.serasa.consume_api_github_repositories.view_model.PullRequestViewModel

class PullRequestFragment : Fragment(R.layout.pull_request_fragment) {

    companion object {
        fun newInstance(userName: String, repo: String): PullRequestFragment {
            return PullRequestFragment().apply {
                Bundle().let {
                    it.putString("owner_key", userName)
                    it.putString("repo_key", repo)
                    this.arguments = it
                }
            }
        }
    }

    private lateinit var binding: PullRequestFragmentBinding
    private lateinit var viewModel: PullRequestViewModel
    private lateinit var adapter: AdapterPullRequestGitHub
    private lateinit var recyclerView: RecyclerView

    private val observerPullRequest = Observer<List<PullRequest>> {
        if (it.isEmpty()) {
            binding.progressBarPullRequest.visibility = INVISIBLE
            Snackbar.make(requireView(), getString(R.string.this_repository_hasnt_pull_requests), Snackbar.LENGTH_LONG).show()
        } else {
            adapter.refresh(it)
            binding.progressBarPullRequest.visibility = INVISIBLE
        }
    }

    private val observerError = Observer<String> {
        binding.textViewErrorPullRequest.visibility = VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadComponents(view)
        executeApplication()
        backToRepositories()
    }

    fun loadComponents(view: View) {
        binding = PullRequestFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(PullRequestViewModel::class.java)
        adapter = AdapterPullRequestGitHub {
            Intent(Intent.ACTION_VIEW, Uri.parse(it.pagePR)).apply {
                startActivity(this)
            }
        }
        recyclerView = binding.recyclerViewPullRequest
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    fun executeApplication() {
        viewModel.pullRequest.observe(viewLifecycleOwner, observerPullRequest)
        viewModel.error.observe(viewLifecycleOwner, observerError)

        viewModel.getPullRequest(
            arguments?.getString("owner_key").toString(),
            arguments?.getString("repo_key").toString()
        )

        binding.textViewTitleHeader.text = arguments?.getString("repo_key")

    }

    fun backToRepositories() {
        binding.imageViewBackArrow.setOnClickListener {
            requireActivity().replaceView(SearchRepositoriesFragment.newInstance())
        }
    }

}