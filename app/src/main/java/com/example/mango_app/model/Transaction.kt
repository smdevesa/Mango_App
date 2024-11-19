package com.example.mango_app.model

import java.util.Date

data class Transaction (
    val id: String = "",
    val description: String = "",
    val amount: Double = 0.0,
    val date: Date = Date()
)