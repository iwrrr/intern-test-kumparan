package com.intern.test.data.entities.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.intern.test.data.entities.user.Address
import com.intern.test.data.entities.user.Company
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(

	@SerializedName("id")
	val id: Int,

	@SerializedName("name")
	val name: String,

	@SerializedName("username")
	val username: String,

	@SerializedName("email")
	val email: String,

	@SerializedName("address")
	val address: Address,

	@SerializedName("phone")
	val phone: String,

	@SerializedName("website")
	val website: String,

	@SerializedName("company")
	val company: Company

) : Parcelable