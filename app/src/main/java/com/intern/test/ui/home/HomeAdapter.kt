package com.intern.test.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.intern.test.data.entities.post.Post
import com.intern.test.databinding.ItemPostsBinding

class HomeAdapter(
    private val onClick: (Post) -> Unit
) : ListAdapter<Post, HomeAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val post = currentList[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = currentList.size

    inner class HomeViewHolder(private val binding: ItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.apply {
                tvPostTitle.text = post.title
                tvPostBody.text = post.body
                tvUserName.text = post.user.name
                tvUserCompany.text = post.user.company.name
            }

            itemView.setOnClickListener {
                onClick(post)
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }
}