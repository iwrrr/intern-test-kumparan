package com.intern.test.data.entities.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(

	@SerializedName("street")
	val street: String,

	@SerializedName("suite")
	val suite: String,

	@SerializedName("city")
	val city: String,

	@SerializedName("zipcode")
	val zipcode: String,

	@SerializedName("geo")
	val geo: Geo

) : Parcelable