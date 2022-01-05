package com.intern.test.data.repositories

import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.album.Photo
import com.intern.test.data.entities.post.Post
import com.intern.test.data.entities.user.User
import com.intern.test.data.source.RemoteDataSource
import com.intern.test.util.Resource
import javax.inject.Inject

class KumparanRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : KumparanRepository {

    override suspend fun getPosts(): Resource<List<Post>> =
        remoteDataSource.getPosts()

    override suspend fun getAlbums(userId: Int): Resource<List<Album>> =
        remoteDataSource.getAlbums(userId)
}