package com.intern.test.ui.detail.user

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.album.Photo
import com.intern.test.databinding.ItemAlbumsBinding
import com.intern.test.ui.detail.user.photo.DetailPhotoActivity

class AlbumAdapter(
    private val context: Context
) : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = currentList[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int = currentList.size

    private fun setPhotos(rvPhotos: RecyclerView, photos: List<Photo>) {
        val photoAdapter = PhotoAdapter(::onItemClicked)

        rvPhotos.layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL)
        rvPhotos.adapter = photoAdapter

        photoAdapter.submitList(photos)
    }

    private fun onItemClicked(photo: Photo) {
        Intent(context, DetailPhotoActivity::class.java).also { intent ->
            intent.putExtra(DetailPhotoActivity.EXTRA_PHOTO, photo)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
//        Toast.makeText(context, photo.title, Toast.LENGTH_SHORT).show()
    }

    inner class AlbumViewHolder(private val binding: ItemAlbumsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.apply {
                tvAlbumTitle.text = album.title
                setPhotos(rvPhotos, album.photos)
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }
        }
    }
}