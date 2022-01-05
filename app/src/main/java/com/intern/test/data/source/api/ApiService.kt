package com.intern.test.data.source.api

import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.album.Photo
import com.intern.test.data.entities.post.Post
import com.intern.test.data.entities.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts?_expand=user&_embed=comments")
    suspend fun getPosts(): Response<List<Post>>

    @GET("user/{userId}/albums?_embed=photos")
    suspend fun getAlbums(
        @Path("userId") id: Int
    ): Response<List<Album>>
}