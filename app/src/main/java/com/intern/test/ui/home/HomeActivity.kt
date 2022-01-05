package com.intern.test.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.test.data.entities.post.Post
import com.intern.test.databinding.ActivityHomeBinding
import com.intern.test.ui.detail.post.DetailPostActivity
import com.intern.test.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(::onItemClicked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setPosts()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setPosts() {
        binding?.apply {
            rvPosts.layoutManager = LinearLayoutManager(applicationContext)
            rvPosts.adapter = homeAdapter
        }

        viewModel.getPosts()
        viewModel.posts.observe(this, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    homeAdapter.submitList(resource.data)
                }
                Status.ERROR -> {
                    hideLoading()
                }
                Status.LOADING -> {
                    showLoading()
                }
            }
        })
    }

    private fun onItemClicked(post: Post) {
        Intent(applicationContext, DetailPostActivity::class.java).also { intent ->
            intent.putExtra(DetailPostActivity.EXTRA_POST, post)
            startActivity(intent)
        }
    }

    private fun showLoading() {
        binding?.apply {
            progressBar.isVisible = true
            rvPosts.isVisible = false
        }
    }

    private fun hideLoading() {
        binding?.apply {
            progressBar.isVisible = false
            rvPosts.isVisible = true
        }
    }
}