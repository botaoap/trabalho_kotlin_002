package com.serasa.consume_api_github_repositories.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.serasa.consume_api_github_repositories.R
import com.serasa.consume_api_github_repositories.databinding.BottomSheetFragmentBinding
import com.serasa.consume_api_github_repositories.model.ItemsGitHub
import com.serasa.consume_api_github_repositories.model.UserDetail
import com.serasa.consume_api_github_repositories.view_model.BottomSheetViewModel

class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(userName: String): BottomSheetFragment {
            return BottomSheetFragment().apply {
                Bundle().let {
                    it.putString("user_detail_key", userName)
                    this.arguments = it
                }
            }
        }
    }

    private lateinit var binding: BottomSheetFragmentBinding
    private lateinit var viewModel: BottomSheetViewModel

    private val observerUserDetail = Observer<UserDetail> {
        binding.textViewUsernameDetail.text = arguments?.getString("user_detail_key")
        binding.textViewNameDetail.text = it.name
        binding.textViewLocationDetail.text = it.location
        binding.textViewDescriptionRespositorie.text = it.bio
        Glide.with(requireView())
            .load(it.avatar)
            .placeholder(R.drawable.ic_error)
            .into(binding.imageViewAvatarDetail)
    }

    private val observerError = Observer<String> {
        Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadComponents(view)
        executeApplication()

    }

    fun loadComponents(view: View) {
        binding = BottomSheetFragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(BottomSheetViewModel::class.java)
    }

    fun executeApplication() {
        viewModel.userDetail.observe(viewLifecycleOwner, observerUserDetail)
        viewModel.error.observe(viewLifecycleOwner, observerError)

        val args = arguments?.getString("user_detail_key")

        if (!args.isNullOrEmpty()) {
            viewModel.fetchUserDetail(args)
        }
    }

    override fun getTheme() = R.style.CustomBottomSheetDialog

}