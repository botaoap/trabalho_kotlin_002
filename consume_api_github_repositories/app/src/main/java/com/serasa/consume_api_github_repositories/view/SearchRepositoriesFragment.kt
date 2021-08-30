package com.serasa.consume_api_github_repositories.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serasa.consume_api_github_repositories.R
import com.serasa.consume_api_github_repositories.adapter.AdapterRepositoryGitHub
import com.serasa.consume_api_github_repositories.databinding.SearchRepositoriesFragmentBinding
import com.serasa.consume_api_github_repositories.model.ItemsGitHub
import com.serasa.consume_api_github_repositories.utils.ClickItemRepository
import com.serasa.consume_api_github_repositories.utils.replaceView
import com.serasa.consume_api_github_repositories.view_model.PullRequestViewModel
import com.serasa.consume_api_github_repositories.view_model.SearchRepositoriesViewModel

class SearchRepositoriesFragment : Fragment(R.layout.search_repositories_fragment), ClickItemRepository {

    companion object {
        fun newInstance() = SearchRepositoriesFragment()
    }

    private lateinit var binding: SearchRepositoriesFragmentBinding
    private lateinit var viewModel: SearchRepositoriesViewModel
    private lateinit var viewModelPullRquest: PullRequestViewModel
    private lateinit var adapter: AdapterRepositoryGitHub
    private lateinit var recyclerView: RecyclerView

    private val observerRepository = Observer<List<ItemsGitHub>> {
        adapter.refresh(it)
//        binding.progressBarRepositories.visibility = INVISIBLE
        binding.SplashScreenProgrammer.visibility = INVISIBLE
    }

    private val observerError = Observer<String> {
        binding.SplashScreenProgrammer.visibility = INVISIBLE
        binding.textViewErrorRepositorie.visibility = VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadComponents(view)
        executeApplication()

    }

    fun loadComponents(view: View) {
        binding = SearchRepositoriesFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(SearchRepositoriesViewModel::class.java)
        viewModelPullRquest = ViewModelProvider(this).get(PullRequestViewModel::class.java)
        adapter = AdapterRepositoryGitHub(this)
        recyclerView = binding.recyclerViewRepositories
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    fun executeApplication() {
        viewModel.repository.observe(viewLifecycleOwner, observerRepository)
        viewModel.error.observe(viewLifecycleOwner, observerError)
//        'q', "Java", "fork", "desc"

        viewModel.getSearchRepository()


    }

    override fun onClickRepository(repo: ItemsGitHub) {
        viewModelPullRquest.getPullRequest(repo.owner.userName, repo.title)
        requireActivity().replaceView(
            PullRequestFragment
                .newInstance(
                    repo.owner.userName,
                    repo.title
                )
        )
    }

    override fun onClickUserDetail(repo: ItemsGitHub) {
        BottomSheetFragment.newInstance(repo.owner.userName).let {
            it.show(parentFragmentManager, "bottom_sheet_key")
        }
    }
}