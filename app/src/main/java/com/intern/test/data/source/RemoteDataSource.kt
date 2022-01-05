package com.intern.test.data.source

import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.post.Post
import com.intern.test.util.Resource

interface RemoteDataSource {

    suspend fun getPosts(): Resource<List<Post>>

    suspend fun getAlbums(userId: Int): Resource<List<Album>>
}