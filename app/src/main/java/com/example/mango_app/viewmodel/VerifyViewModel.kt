package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.R
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.VerifyRequest
import com.example.mango_app.model.data.VerifyResponse
import com.example.mango_app.ui.screen.VerifyEvent
import com.example.mango_app.utils.ErrorMessagesProvider
import kotlinx.coroutines.launch
import retrofit2.Response

class VerifyViewModel(private val apiService: ApiService) : ViewModel() {

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
                        _event.postValue(VerifyEvent.Error(ErrorMessagesProvider.getErrorMessage(R.string.invalid_code)))
                    }
                } catch (e: Exception) {
                    _event.postValue(VerifyEvent.Error(ErrorMessagesProvider.getErrorMessage(R.string.internal_error)))
                }
            }
        }
    }

    private fun isValidVerifyForm(verificationCode: String): Boolean {
        return verificationCode.isNotEmpty()
    }

}