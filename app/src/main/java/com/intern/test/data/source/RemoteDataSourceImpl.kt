package com.intern.test.data.source

import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.album.Photo
import com.intern.test.data.entities.post.Post
import com.intern.test.data.entities.user.User
import com.intern.test.data.source.api.ApiService
import com.intern.test.util.Resource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getPosts(): Resource<List<Post>> {
        val response = apiService.getPosts()
        val result = response.body()

        return if (response.isSuccessful) {
            Resource.Success(result)
        } else {
            Resource.Error(response.message())
        }
    }

    override suspend fun getAlbums(userId: Int): Resource<List<Album>> {
        val response = apiService.getAlbums(userId)
        val result = response.body()

        return if (response.isSuccessful) {
            Resource.Success(result)
        } else {
            Resource.Error(response.message())
        }
    }
}