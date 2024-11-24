package com.example.mango_app.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.R
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.UserDataStore
import com.example.mango_app.model.data.LoginRequest
import com.example.mango_app.model.data.LoginResponse
import com.example.mango_app.ui.screen.LoginEvent
import com.example.mango_app.utils.ErrorMessagesProvider
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val apiService: ApiService, private val userDataStore: UserDataStore) : ViewModel() {

    // Live data
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _event = MutableLiveData<LoginEvent>()
    val event: LiveData<LoginEvent> = _event

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isLoginFormValid(email, password)
    }

    fun onLoginClick() {
        if(isLoginFormValid(email.value!!, password.value!!)) {
            _event.postValue(LoginEvent.Loading)
            val req = LoginRequest(email.value!!, password.value!!)
            viewModelScope.launch {
                try {
                    val response: Response<LoginResponse> = apiService.loginUser(req)
                    if(response.isSuccessful) {
                        userDataStore.saveAuthToken(response.body()!!.token)
                        fetchUserInfo()
                        _event.postValue(LoginEvent.Success)
                    } else {
                        _event.postValue(LoginEvent.Error(ErrorMessagesProvider.getErrorMessage(R.string.login_error)))
                    }
                } catch (e: Exception) {
                    _event.postValue(LoginEvent.Error(ErrorMessagesProvider.getErrorMessage(R.string.internal_error)))
                }
            }
        }
    }

    private suspend fun fetchUserInfo() {
        val response = apiService.getUserInfo()
        if(response.isSuccessful) {
            userDataStore.saveUserInfo(response.body()!!)
        }
    }

    private fun isLoginFormValid(email: String, password: String): Boolean {
        return CommonValidations.isValidEmail(email) && password.isNotEmpty()
    }

}