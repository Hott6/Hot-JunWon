package org.techtown.soptseminar.data.entity.follower

import com.google.gson.annotations.SerializedName

data class ResponseUserInfoData(
    @SerializedName("avatar_url")
    val image: String,
    @SerializedName("login")
    val name: String,
    @SerializedName("bio")
    val introduce: String
)
