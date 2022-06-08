package org.techtown.soptseminar.data.entity.signin

import com.google.gson.annotations.SerializedName

data class RequestSignInData(
    @SerializedName("email") val id: String,
    val password: String
)
