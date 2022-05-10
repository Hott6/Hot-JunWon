package org.techtown.soptseminar.week4

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("auth/signin")
    fun postSignIn(
        @Body body: RequestSignInData
    ): Call<ResponseSignInData>
}
