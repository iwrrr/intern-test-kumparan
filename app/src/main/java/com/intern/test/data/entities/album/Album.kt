package com.intern.test.data.entities.album

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(

	@SerializedName("userId")
	val userId: Int,

	@SerializedName("id")
	val id: Int,

	@SerializedName("title")
	val title: String,

	@SerializedName("photos")
	val photos: List<Photo>,

) : Parcelable
