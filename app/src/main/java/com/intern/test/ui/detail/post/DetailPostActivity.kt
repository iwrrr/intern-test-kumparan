package com.intern.test.ui.detail.post

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.test.data.entities.post.Post
import com.intern.test.databinding.ActivityDetailPostBinding
import com.intern.test.ui.detail.user.DetailUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPostActivity : AppCompatActivity() {

    private var _binding: ActivityDetailPostBinding? = null
    private val binding get() = _binding

    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val post = intent.getParcelableExtra<Post>(EXTRA_POST) as Post

        setDetailPost(post)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setDetailPost(post: Post) {
        binding?.apply {
            tvUserName.text = post.user.name
            tvUserCompany.text = post.user.company.name
            tvPostTitle.text = post.title
            tvPostBody.text = post.body
        }

        binding?.tvUserName?.setOnClickListener {
            Intent(applicationContext, DetailUserActivity::class.java).also { intent ->
                intent.putExtra(DetailUserActivity.EXTRA_USER, post.user)
                startActivity(intent)
            }
        }

        setComments(post)
    }

    private fun setComments(post: Post) {
        binding?.apply {
            rvComments.layoutManager = LinearLayoutManager(applicationContext)
            rvComments.adapter = commentAdapter
        }

        commentAdapter.submitList(post.comments)
    }

    companion object {
        const val EXTRA_POST = "extra_post"
    }
}