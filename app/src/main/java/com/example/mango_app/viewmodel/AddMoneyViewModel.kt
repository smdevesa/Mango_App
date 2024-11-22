package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.Card
import com.example.mango_app.model.data.RechargeRequest
import kotlinx.coroutines.launch

class AddMoneyViewModel(private val apiService: ApiService) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _cardId = MutableLiveData<Int>()
    val cardId: LiveData<Int> = _cardId

    init {
        fetchCards()
    }

    private fun fetchCards() {
        viewModelScope.launch {
            val response = apiService.getCards()
            if (response.isSuccessful) {
                _cards.value = response.body()
            }
        }
    }

    fun setAmount(amount: String) {
        _amount.value = amount
    }

    fun setCardId(cardId: Int) {
        _cardId.value = cardId
    }

    fun addMoney() {
        if(_amount.value.isNullOrEmpty()) return
        viewModelScope.launch {
            val req = RechargeRequest(_amount.value?.toDouble() ?: 0.0)
            val response = apiService.rechargeWallet(req)
        }
    }
}