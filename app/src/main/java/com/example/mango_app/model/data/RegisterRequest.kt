package com.example.mango_app.model.data

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String,
)
