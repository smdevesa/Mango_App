package com.example.mango_app.model.data

data class Card (
    val id: Int,
    val cardNumber: String,
    val type : String,
    val fullName: String,
    val expirationDate: String,
    val cvv: String
)