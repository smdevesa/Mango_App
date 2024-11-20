package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mango_app.model.ApiService

class LoginViewModel(apiService: ApiService) : ViewModel() {

    // Live data
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isLoginFormValid(email, password)
    }

    fun onLoginClick() {
        // TODO: Llamar a la API para hacer login
    }

    private fun isLoginFormValid(email: String, password: String): Boolean {
        return CommonValidations.isValidEmail(email) && CommonValidations.isValidPassword(password)
    }

}