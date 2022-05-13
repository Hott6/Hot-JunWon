package org.techtown.soptseminar.week4

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUpData
    ): Call<ResponseSignUpData>

    @POST("auth/signin")
    fun postSignIn(
        @Body body: RequestSignInData
    ): Call<ResponseSignInData>
}
