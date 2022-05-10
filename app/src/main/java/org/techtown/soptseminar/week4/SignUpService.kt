package org.techtown.soptseminar.week4

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("auth/signup")
    fun postSignup(
        @Body body: RequestSignUpData
    ): Call<ResponseSignUpData>
}
