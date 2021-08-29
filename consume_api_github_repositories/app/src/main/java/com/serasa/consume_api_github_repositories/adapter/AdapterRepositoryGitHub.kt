package com.serasa.consume_api_github_repositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serasa.consume_api_github_repositories.R
import com.serasa.consume_api_github_repositories.databinding.ItemRepositoriesBinding
import com.serasa.consume_api_github_repositories.model.ItemsGitHub
import com.serasa.consume_api_github_repositories.utils.ClickItemRepository

class AdapterRepositoryGitHub(val onClickRepository: ClickItemRepository) : RecyclerView.Adapter<ItemRepositoriesViewHolder>() {

    private val listOfRepositories = mutableListOf<ItemsGitHub>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRepositoriesViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_repositories, parent, false).apply {
            return ItemRepositoriesViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ItemRepositoriesViewHolder, position: Int) {
        listOfRepositories[position].apply {
            holder.bind(this)
            holder.itemView.findViewById<ImageView>(R.id.imageViewAvatarRepositorie).setOnClickListener {
                onClickRepository.onClickUserDetail(this)
            }
            holder.itemView.setOnClickListener {
                onClickRepository.onClickRepository(this)
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfRepositories.size
    }

    fun refresh(newList: List<ItemsGitHub>) {
        listOfRepositories.clear()
        listOfRepositories.addAll(newList)
        notifyDataSetChanged()
    }
}

class ItemRepositoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var binding = ItemRepositoriesBinding.bind(itemView)

    fun bind(item: ItemsGitHub) {
        binding.textViewTitleRepositorie.text = item.title
        binding.textViewTitleRepositorie.setTextColor(getColor(itemView.context, R.color.blue))
        binding.textViewDescriptionRespositorie.text = item.description
        binding.textViewUsernameRepositorie.text = item.owner.userName
        binding.includeFokStar.textViewForkCount.text = item.forkCount.toString()
        binding.includeFokStar.textViewForkCount.setTextColor(getColor(itemView.context,R.color.gold))
        binding.includeFokStar.textViewStarCount.text = item.startCount.toString()
        binding.includeFokStar.textViewStarCount.setTextColor(getColor(itemView.context,R.color.gold))
        binding.includeFokStar.imageViewFork.setColorFilter(getColor(itemView.context,R.color.gold))
        binding.includeFokStar.imageViewStar.setColorFilter(getColor(itemView.context,R.color.gold))
        binding.imageViewAvatarRepositorie.apply {
            Glide.with(itemView)
                .load(item.owner.userAvatar)
                .placeholder(R.drawable.ic_error)
                .into(this)
        }
    }
}