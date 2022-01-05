package com.intern.test.data.repositories

import com.intern.test.repositories.FakeKumparanRepository
import com.intern.test.util.DataDummy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class KumparanRepositoryImplTest {

    @Mock
    private lateinit var fakeKumparanRepository: FakeKumparanRepository

    private val postResponses = DataDummy.generateRemoteDummyPosts()
    private val albumResponses = DataDummy.generateRemoteDummyAlbums()

    private val userId = 1

    @Test
    fun `get all posts`() = runBlockingTest {
        `when`(fakeKumparanRepository.getPosts()).thenReturn(postResponses)

        val postEntities = fakeKumparanRepository.getPosts()
        verify(fakeKumparanRepository).getPosts()

        assertNotNull(postEntities)
        assertEquals(postResponses.data?.size?.toLong(), postEntities.data?.size?.toLong())
    }

    @Test
    fun `get all albums`() = runBlockingTest {
        `when`(fakeKumparanRepository.getAlbums(userId)).thenReturn(albumResponses)

        val albumEntities = fakeKumparanRepository.getAlbums(userId)
        verify(fakeKumparanRepository).getAlbums(userId)

        assertNotNull(albumEntities)
        assertEquals(albumResponses.data?.size?.toLong(), albumEntities.data?.size?.toLong())
    }
}