package org.techtown.soptseminar.week4

data class ResponseWrapper<T>(
    val status: Int,
    val message: String,
    val data: T?
)