package com.example.mango_app.model

import com.example.mango_app.model.data.RegisterRequest
import com.example.mango_app.model.data.RegisterResponse
import com.example.mango_app.model.data.VerifyRequest
import com.example.mango_app.model.data.VerifyResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/user")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    @POST("api/user/verify")
    suspend fun verifyUser(
        @Body verifyRequest: VerifyRequest
    ): Response<VerifyResponse>
}

object RetrofitServiceFactory {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    fun makeRetrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}