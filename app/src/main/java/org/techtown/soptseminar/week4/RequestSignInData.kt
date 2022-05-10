package org.techtown.soptseminar.week4

import com.google.gson.annotations.SerializedName

data class RequestSignInData(
    @SerializedName("email") val id: String,
    val password: String
)
