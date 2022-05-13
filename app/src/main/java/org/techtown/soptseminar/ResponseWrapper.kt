package org.techtown.soptseminar

data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)