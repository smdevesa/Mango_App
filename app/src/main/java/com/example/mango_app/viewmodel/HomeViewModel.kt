package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.UserDataStore
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: ApiService, private val userDataStore: UserDataStore) : ViewModel() {

    private val _firstName: MutableLiveData<String> = MutableLiveData()
    val firstName: LiveData<String> = _firstName

    private val _lastName: MutableLiveData<String> = MutableLiveData()
    val lastName: LiveData<String> = _lastName

    private val _balance: MutableLiveData<Int> = MutableLiveData()
    val balance: LiveData<Int> = _balance

    private val _cvu: MutableLiveData<String> = MutableLiveData()
    val cvu: LiveData<String> = _cvu

    private val _alias: MutableLiveData<String> = MutableLiveData()
    val alias: LiveData<String> = _alias


    init {
        fetchUserInfo()
        updateWalletInfo()
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            userDataStore.getUserInfo().collect { userInfo ->
                _firstName.postValue(userInfo?.firstName ?: "")
                _lastName.postValue(userInfo?.lastName ?: "")
            }
        }
    }

    private fun updateWalletInfo() {
        viewModelScope.launch {
            val response = apiService.getWalletDetails()
            if (response.isSuccessful) {
                val walletDetails = response.body()
                _balance.postValue(walletDetails?.balance ?: 0)
                _cvu.postValue(walletDetails?.cbu ?: "")
                _alias.postValue(walletDetails?.alias ?: "")
            }
        }
    }

}