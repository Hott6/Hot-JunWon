package org.techtown.soptseminar.data.api

import org.techtown.soptseminar.data.entity.signin.RequestSignInData
import org.techtown.soptseminar.data.entity.signin.ResponseSignInData
import org.techtown.soptseminar.data.entity.signup.RequestSignUpData
import org.techtown.soptseminar.data.entity.signup.ResponseSignUpData
import org.techtown.soptseminar.util.ResponseWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUpData
    ): Call<ResponseWrapper<ResponseSignUpData>>

    @POST("auth/signin")
    fun postSignIn(
        @Body body: RequestSignInData
    ): Call<ResponseWrapper<ResponseSignInData>>
}
