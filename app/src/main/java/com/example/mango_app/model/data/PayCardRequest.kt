package com.example.mango_app.model.data

data class PayCardRequest(
    val type: String = "CARD",
    val cardId: Int,
)
