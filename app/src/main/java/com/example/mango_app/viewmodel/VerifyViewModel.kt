package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.VerifyRequest
import com.example.mango_app.model.data.VerifyResponse
import com.example.mango_app.ui.screen.VerifyEvent
import kotlinx.coroutines.launch
import retrofit2.Response

class VerifyViewModel(private val apiService: ApiService) : ViewModel() {

    // Livedata
    private val _verificationCode = MutableLiveData<String>()
    val verificationCode: LiveData<String> = _verificationCode

    private val _verifyEnable = MutableLiveData<Boolean>()
    val verifyEnable: LiveData<Boolean> = _verifyEnable

    private val _event = MutableLiveData<VerifyEvent>()
    val event: LiveData<VerifyEvent> = _event

    fun onVerifyChanged(verificationCode: String) {
        _verificationCode.value = verificationCode
        _verifyEnable.value = isValidVerifyForm(verificationCode)
    }

    fun onVerifyClick() {
        if (isValidVerifyForm(verificationCode.value!!)) {
            _event.postValue(VerifyEvent.Loading)
            val req = VerifyRequest(
                code = verificationCode.value!!
            )
            viewModelScope.launch {
                try {
                    val response: Response<VerifyResponse> = apiService.verifyUser(req)
                    if (response.isSuccessful) {
                        _event.postValue(VerifyEvent.VerifySuccess)
                    } else {
                        _event.postValue(VerifyEvent.Error(response.errorBody()?.string() ?: "Error"))
                    }
                } catch (e: Exception) {
                    _event.postValue(e.message?.let { VerifyEvent.Error(it) })
                }
            }
        }
    }

    private fun isValidVerifyForm(verificationCode: String): Boolean {
        return verificationCode.isNotEmpty()
    }

}