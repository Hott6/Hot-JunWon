package org.techtown.soptseminar.week4

data class ResponseSignUpData(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val id: Int
    )
}
