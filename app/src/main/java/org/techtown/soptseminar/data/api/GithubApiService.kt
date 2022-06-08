package org.techtown.soptseminar.data.api

import org.techtown.soptseminar.data.entity.repository.ResponseRepoInfoData
import org.techtown.soptseminar.data.entity.follower.ResponseUserInfoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("users/{user_name}/repos")
    fun getRepoInfo(
        @Path("user_name") userName: String
    ): Call<List<ResponseRepoInfoData>>

    @GET("users/{user_name}/followers")
    fun getFollowingInfo(
        @Path("user_name") userName: String
    ): Call<List<ResponseUserInfoData>>
}
