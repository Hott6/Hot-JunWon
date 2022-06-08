package org.techtown.soptseminar.data.entity.signup

import com.google.gson.annotations.SerializedName

data class RequestSignUpData(
    @SerializedName("email")
    val id: String,
    val name: String,
    val password: String
)
