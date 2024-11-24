package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.Card
import com.example.mango_app.model.data.PayCardRequest
import com.example.mango_app.model.data.PayLinkBalanceRequest
import kotlinx.coroutines.launch

class PayViewModel(private val apiService: ApiService) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    private val _cardId = MutableLiveData<Int>()
    val cardId: LiveData<Int> = _cardId

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _successMessageVisible = MutableLiveData(false)
    val successMessageVisible: LiveData<Boolean> = _successMessageVisible

    private val _isLinkValid = MutableLiveData<Boolean>()
    val isLinkValid: LiveData<Boolean> = _isLinkValid

    private val _insufficientFunds = MutableLiveData<Boolean>()
    val insufficientFunds: LiveData<Boolean> = _insufficientFunds

    init {
        fetchCards()
    }

    fun validatePaymentLinkWithNavigation(linkUuid: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.getPaymentDetails(linkUuid)
                val isValid = response.isSuccessful
                _isLinkValid.value = isValid
                onResult(isValid)
            } catch (e: Exception) {
                _isLinkValid.value = false
                onResult(false)
            }
        }
    }

    private fun fetchCards() {
        viewModelScope.launch {
            val response = apiService.getCards()
            if (response.isSuccessful) {
                _cards.value = response.body()
            }
        }
    }
    fun setCardId(cardId: Int) {
        _cardId.value = cardId
    }


    suspend fun fetchPaymentDetails(linkUuid: String) {
        try {
            val response = apiService.getPaymentDetails(linkUuid)

            if (response.isSuccessful) {
                val paymentDetails = response.body()
                val amount = paymentDetails?.payment?.amount
                _amount.value = amount?.toString()
            } else {
                //
            }
        } catch (e: Exception) {
            //
        }
    }

    fun payWithBalance(linkUuid: String) {
        viewModelScope.launch {
            try {
                val requestBody = PayLinkBalanceRequest()

                val response = apiService.paymentBalanceWithLink(linkUuid, requestBody)

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.success == true) {
                        _successMessageVisible.postValue(true)
                        _insufficientFunds.postValue(false)
                    } else {
                        _successMessageVisible.postValue(false)
                        _insufficientFunds.postValue(true)
                    }
                } else {
                    _successMessageVisible.postValue(false)
                    _insufficientFunds.postValue(true)
                }
            } catch (e: Exception) {
                _successMessageVisible.postValue(false)
                _insufficientFunds.postValue(true)
            }
        }
    }


    fun payWithCard(linkUuid: String, cardId: Int) {
        viewModelScope.launch {
            try {
                val requestBody = PayCardRequest(cardId = cardId)

                val response = apiService.paymentCardWithLink(linkUuid, requestBody)

                if (response.isSuccessful && response.body()?.success == true) {
                    _successMessageVisible.postValue(true)
                } else {
                    _successMessageVisible.postValue(false)
                }
            } catch (e: Exception) {
                _successMessageVisible.postValue(false)
            }
        }
    }

    fun resetSuccessMessage() {
        _successMessageVisible.value = false
    }

    fun resetInsufficientFunds() {
        _insufficientFunds.value = false
    }



}