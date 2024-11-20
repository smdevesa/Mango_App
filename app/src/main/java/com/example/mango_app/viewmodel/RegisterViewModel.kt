package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mango_app.model.ApiService

class RegisterViewModel(apiService: ApiService) : ViewModel() {

    // Live data
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

    fun onRegisterChanged(fullName: String, email: String, phone: String, password: String, repeatPassword: String) {
        _fullName.value = fullName
        _email.value = email
        _phone.value = phone
        _password.value = password
        _repeatPassword.value = repeatPassword
        _registerEnable.value = isValidRegisterForm(fullName, email, phone, password, repeatPassword)
    }

    fun onRegisterClick() {
        // TODO: Aquí iría la lógica para registrar al usuario
    }

    private fun isValidRegisterForm(fullName: String, email: String, phone: String, password: String, repeatPassword: String): Boolean {
        return CommonValidations.isValidFullName(fullName) &&
                CommonValidations.isValidEmail(email) &&
                CommonValidations.isValidPhone(phone) &&
                CommonValidations.isValidPassword(password) &&
                CommonValidations.isValidRepeatPassword(password, repeatPassword)
    }
}