package com.example.mango_app.model.data

data class WalletDetailsResponse(
    val id: Int,
    val balance: Int,
    val invested: Int,
    val cbu: String,
    val alias: String,
    val createdAt: String,
    val updatedAt: String
)
