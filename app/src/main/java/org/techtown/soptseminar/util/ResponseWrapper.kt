package org.techtown.soptseminar.util

data class ResponseWrapper<T>(
    val status: Int,
    val message: String,
    val data: T?
)