package com.intern.test.ui.detail.user.photo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intern.test.data.entities.album.Photo
import com.intern.test.databinding.ActivityDetailPhotoBinding
import com.intern.test.util.Extension.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPhotoActivity : AppCompatActivity() {

    private var _binding: ActivityDetailPhotoBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailPhotoBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val photo = intent.getParcelableExtra<Photo>(EXTRA_PHOTO) as Photo

        setDetailPhoto(photo)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setDetailPhoto(photo: Photo) {
        binding?.apply {
            tvPhotoTitle.text = photo.title
            ivPhoto.loadImage(photo.thumbnailUrl)
        }
    }

    companion object {
        const val EXTRA_PHOTO = "extra_photo"
    }
}