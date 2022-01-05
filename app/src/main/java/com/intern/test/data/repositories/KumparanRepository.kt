package com.intern.test.data.repositories

import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.post.Post
import com.intern.test.util.Resource

interface KumparanRepository {

    suspend fun getPosts(): Resource<List<Post>>

    suspend fun getAlbums(userId: Int): Resource<List<Album>>
}