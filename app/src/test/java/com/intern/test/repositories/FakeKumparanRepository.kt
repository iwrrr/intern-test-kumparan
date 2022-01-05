package com.intern.test.repositories

import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.post.Post
import com.intern.test.data.repositories.KumparanRepository
import com.intern.test.util.DataDummy
import com.intern.test.util.Resource

class FakeKumparanRepository : KumparanRepository {

    override suspend fun getPosts(): Resource<List<Post>> =
        DataDummy.generateRemoteDummyPosts()

    override suspend fun getAlbums(userId: Int): Resource<List<Album>> =
        DataDummy.generateRemoteDummyAlbums()
}