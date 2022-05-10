package org.techtown.soptseminar.week4

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"

    // Retrofit 객체 생성
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // 구현체 생성
    val signInService: SignInService = retrofit.create(SignInService::class.java)
    val signUpService: SignUpService = retrofit.create(SignUpService::class.java)
}
