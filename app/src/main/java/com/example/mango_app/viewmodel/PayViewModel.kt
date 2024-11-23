package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.Card

class PayViewModel(private val apiService: ApiService) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _successMessageVisible = MutableLiveData(false)
    val successMessageVisible: LiveData<Boolean> = _successMessageVisible





}