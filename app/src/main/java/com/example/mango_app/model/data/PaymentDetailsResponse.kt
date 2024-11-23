package com.example.mango_app.model.data

data class PaymentDetailsResponse(
    val payment: Payment
)

data class Payment(
    val id: Int,
    val type: String,
    val amount: Int,
    val balanceBefore: Int,
    val balanceAfter: Int,
    val receiverBalanceBefore: Int?,
    val receiverBalanceAfter: Int?,
    val pending: Boolean,
    val linkUuid: String,
    val createdAt: String,
    val updatedAt: String,
    val receiver: Receiver
)

data class Receiver(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
)