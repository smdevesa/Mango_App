package com.example.mango_app.model

import com.example.mango_app.model.data.Card
import com.example.mango_app.model.data.LoginRequest
import com.example.mango_app.model.data.LoginResponse
import com.example.mango_app.model.data.PayCardRequest
import com.example.mango_app.model.data.PayLinkBalanceRequest
import com.example.mango_app.model.data.PaymentDetailsResponse
import com.example.mango_app.model.data.PaymentLinkResponse
import com.example.mango_app.model.data.RechargeRequest
import com.example.mango_app.model.data.RegisterRequest
import com.example.mango_app.model.data.RegisterResponse
import com.example.mango_app.model.data.UserInfoResponse
import com.example.mango_app.model.data.VerifyRequest
import com.example.mango_app.model.data.VerifyResponse
import com.example.mango_app.model.data.WalletDetailsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/user")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    @GET("api/user")
    suspend fun getUserInfo(): Response<UserInfoResponse>

    @POST("api/user/verify")
    suspend fun verifyUser(
        @Body verifyRequest: VerifyRequest
    ): Response<VerifyResponse>

    @POST("api/user/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET("api/wallet/cards")
    suspend fun getCards(): Response<List<Card>>

    @DELETE("api/wallet/cards/{cardId}")
    suspend fun deleteCard(
        @Path("cardId") cardId: Int
    ): Response<Unit>

    @POST("api/wallet/recharge")
    suspend fun rechargeWallet(
        @Body rechargeRequest: RechargeRequest
    ): Response<Unit>

    @GET("api/wallet/details")
    suspend fun getWalletDetails(): Response<WalletDetailsResponse>

    @GET("api/payment/link/{linkUuid}")
    suspend fun getPaymentDetails(
        @Path("linkUuid") linkUuid: String
    ): Response<PaymentDetailsResponse>

    @POST("api/payment/link/{linkUuid}")
    suspend fun paymentCardWithLink(
        @Path("linkUuid") linkUuid: String,
        @Body paymentCardRequest: PayCardRequest,
    ): Response<PaymentLinkResponse>

   @POST("api/payment/link/{linkUuid}")
    suspend fun paymentBalanceWithLink(
       @Path("linkUuid") linkUuid: String,
       @Body paymentBalanceRequest: PayLinkBalanceRequest,
    ): Response<PaymentLinkResponse>

    @POST("api/user/logout")
    suspend fun logout(): Response<Unit>

}

object RetrofitServiceFactory {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    fun makeRetrofitService(userDataStore: UserDataStore): ApiService {

        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(userDataStore))
            .authenticator(TokenAuthenticator(userDataStore))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}