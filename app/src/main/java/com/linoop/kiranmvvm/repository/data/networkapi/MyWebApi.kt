package com.linoop.kiranmvvm.repository.data.networkapi

import com.linoop.kiranmvvm.models.CreateUserResponse
import com.linoop.kiranmvvm.models.LoginResponse
import com.linoop.kiranmvvm.models.UserTable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyWebApi {

    @FormUrlEncoded
    @POST("signup/")
    suspend fun createUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("first_name") first_name: String = "Default",
        @Field("last_name") last_name: String = "Default",
        @Field("is_superuser") is_superuser: Boolean = true,
    ): Response<CreateUserResponse>

    @FormUrlEncoded
    @POST("login/")
    suspend fun checkLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>
}