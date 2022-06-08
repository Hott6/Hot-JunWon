package org.techtown.soptseminar.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"
    private const val BASE_URL_GITHUB = "https://api.github.com/"

    private val soptRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)
    val githubApiService: GithubApiService = githubRetrofit.create(GithubApiService::class.java)
}
