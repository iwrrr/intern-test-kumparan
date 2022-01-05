package com.intern.test.data.entities.post

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.intern.test.data.entities.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(

	@SerializedName("userId")
	val userId: Int,

	@SerializedName("id")
	val id: Int,

	@SerializedName("title")
	val title: String,

	@SerializedName("body")
	val body: String,

	@SerializedName("user")
	val user: User,

	@SerializedName("comments")
	val comments: List<Comment>

) : Parcelable
