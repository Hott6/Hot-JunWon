package org.techtown.soptseminar.week4

data class ResponseSignInData(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val name: String,
        val email: String
    )
}
