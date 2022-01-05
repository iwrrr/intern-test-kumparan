package com.intern.test.ui.detail.user

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.intern.test.data.entities.user.User
import com.intern.test.databinding.ActivityDetailUserBinding
import com.intern.test.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUserActivity : AppCompatActivity() {

    private var _binding: ActivityDetailUserBinding? = null
    private val binding get() = _binding

    private val viewModel: DetailUserViewModel by viewModels()

    private val albumAdapter: AlbumAdapter by lazy {
        AlbumAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        setDetailUser(user)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setDetailUser(user: User) {
        binding?.apply {
            tvUserName.text = user.name
            tvUserEmail.text = user.email
            tvUserStreet.text = user.address.street
            tvUserSuite.text = user.address.suite
            tvUserCity.text = user.address.city
            tvUserZipcode.text = user.address.zipcode
            tvUserCompany.text = user.company.name
        }

        setAlbums(user.id)
    }

    private fun setAlbums(userId: Int) {
        binding?.apply {
            rvAlbums.layoutManager = LinearLayoutManager(applicationContext)
            rvAlbums.adapter = albumAdapter
        }

        viewModel.getAlbums(userId)
        viewModel.album.observe(this, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    albumAdapter.submitList(resource.data)
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

    private fun showLoading() {
        binding?.apply {
            progressBar.isVisible = true
            rvAlbums.isVisible = false
        }
    }

    private fun hideLoading() {
        binding?.apply {
            progressBar.isVisible = false
            rvAlbums.isVisible = true
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}