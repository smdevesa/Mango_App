package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.UserDataStore
import kotlinx.coroutines.launch

class HomeViewModel(apiService: ApiService, userDataStore: UserDataStore) : ViewModel() {

    private val _firstName: MutableLiveData<String> = MutableLiveData()
    val firstName: LiveData<String> = _firstName

    private val _lastName: MutableLiveData<String> = MutableLiveData()
    val lastName: LiveData<String> = _lastName

    init {
        viewModelScope.launch {
            userDataStore.getUserInfo().collect { userInfo ->
                _firstName.postValue(userInfo?.firstName ?: "")
                _lastName.postValue(userInfo?.lastName ?: "")
            }
        }
    }

}