package com.example.mango_app.model

import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val userDataStore: UserDataStore) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // Obtener el token actualizado desde DataStore
        val token = runBlocking {
            userDataStore.getAuthToken().firstOrNull()
        }

        // Si no hay token, no se puede autenticar
        if (token.isNullOrEmpty()) {
            return null
        }

        // Crear una nueva solicitud con el token actualizado
        return response.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
    }
}
