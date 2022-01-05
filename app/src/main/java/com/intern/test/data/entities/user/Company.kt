package com.intern.test.data.entities.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(

	@SerializedName("name")
	val name: String,

	@SerializedName("catchPhrase")
	val catchPhrase: String,

	@SerializedName("bs")
	val bs: String

) : Parcelable