package com.serasa.consume_api_github_repositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serasa.consume_api_github_repositories.R
import com.serasa.consume_api_github_repositories.databinding.ItemPullRequestBinding
import com.serasa.consume_api_github_repositories.databinding.PullRequestFragmentBinding
import com.serasa.consume_api_github_repositories.model.PullRequest
import com.serasa.consume_api_github_repositories.model.RepositoryGitHub

class AdapterPullRequestGitHub(val onClick: (PullRequest) -> Unit): RecyclerView.Adapter<ItemPullRequestViewHolder>() {

    private val listOfPullRequest = mutableListOf<PullRequest>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPullRequestViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_pull_request, parent, false).apply {
            return ItemPullRequestViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ItemPullRequestViewHolder, position: Int) {
        listOfPullRequest[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                onClick(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfPullRequest.size
    }

    fun refresh(newList: List<PullRequest>) {
        listOfPullRequest.clear()
        listOfPullRequest.addAll(newList)
        notifyDataSetChanged()
    }
}

class ItemPullRequestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val binding = ItemPullRequestBinding.bind(itemView)

    fun bind(pullRequest: PullRequest) {
        binding.textViewUsernamePullRequest.text = pullRequest.userPR.usernamePR
        binding.textViewUsernamePullRequest.setTextColor(getColor(itemView.context,R.color.blue))
        binding.textViewTitlePullRequest.text = pullRequest.titlePR
        binding.textViewTitlePullRequest.setTextColor(getColor(itemView.context, R.color.blue))
        binding.textViewBodyPullRequest.text = pullRequest.bodyPR
        binding.textViewCreateDatePullRequest.text = pullRequest.createDatePR
        binding.imageViewAvatarPullRequest.apply {
            Glide.with(itemView)
                .load(pullRequest.userPR.userImagePR)
                .placeholder(R.drawable.ic_error)
                .into(this)
        }
    }
}