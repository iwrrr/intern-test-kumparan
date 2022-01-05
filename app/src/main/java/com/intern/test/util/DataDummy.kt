package com.intern.test.util

import com.intern.test.data.entities.album.Album
import com.intern.test.data.entities.album.Photo
import com.intern.test.data.entities.post.Comment
import com.intern.test.data.entities.post.Post
import com.intern.test.data.entities.user.Address
import com.intern.test.data.entities.user.Company
import com.intern.test.data.entities.user.Geo
import com.intern.test.data.entities.user.User

object DataDummy {

    fun generateRemoteDummyPosts(): Resource<List<Post>> {
        val posts = ArrayList<Post>()

        posts.add(
            Post(
                userId = 1,
                id = 1,
                title = "abc",
                body = "def",
                user = generateRemoteDummyUser(),
                comments = generateRemoteDummyComments()
            )
        )

        posts.add(
            Post(
                userId = 1,
                id = 1,
                title = "abc",
                body = "def",
                user = generateRemoteDummyUser(),
                comments = generateRemoteDummyComments()
            )
        )

        return Resource.Success(posts)
    }

    private fun generateRemoteDummyComments(): List<Comment> {
        val comments = ArrayList<Comment>()

        comments.add(
            Comment(
                postId = 1,
                id = 1,
                name = "id labore ex et quam laborum",
                email = "Eliseo@gardner.biz",
                body = "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
            )
        )

        comments.add(
            Comment(
                postId = 1,
                id = 1,
                name = "id labore ex et quam laborum",
                email = "Eliseo@gardner.biz",
                body = "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
            )
        )

        return comments
    }

    private fun generateRemoteDummyUser(): User {
        return User(
            id = 1,
            name = "aaa",
            username = "bbb",
            email = "ccc",
            address = Address(
                street = "abc",
                suite = "def",
                city = "ghi",
                zipcode = "123",
                geo = Geo(
                    lat = "aaa",
                    lng = "bbb"
                )
            ),
            phone = "123",
            website = "abc.com",
            company = Company(
                name = "aaa",
                catchPhrase = "bbb",
                bs = "ccc"
            )
        )
    }

    fun generateRemoteDummyAlbums(): Resource<List<Album>> {
        val albums = ArrayList<Album>()

        albums.add(
            Album(
                userId = 1,
                id = 1,
                title = "abc",
                photos = generateRemoteDummyPhotos()
            )
        )

        albums.add(
            Album(
                userId = 1,
                id = 1,
                title = "abc",
                photos = generateRemoteDummyPhotos()
            )
        )

        return Resource.Success(albums)
    }

    private fun generateRemoteDummyPhotos(): List<Photo> {
        val photos = ArrayList<Photo>()

        photos.add(
            Photo(
                albumId = 1,
                id = 1,
                title = "abc",
                url = "abc.com",
                thumbnailUrl = "def.com"
            )
        )

        photos.add(
            Photo(
                albumId = 1,
                id = 1,
                title = "abc",
                url = "abc.com",
                thumbnailUrl = "def.com"
            )
        )

        return photos
    }
}