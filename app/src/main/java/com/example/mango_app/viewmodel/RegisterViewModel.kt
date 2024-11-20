package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.RegisterRequest
import com.example.mango_app.model.data.RegisterResponse
import com.example.mango_app.ui.screen.RegisterEvent
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val apiService: ApiService) : ViewModel() {

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

    private val _event = MutableLiveData<RegisterEvent>()
    val event: LiveData<RegisterEvent> = _event

    fun onRegisterChanged(fullName: String, email: String, phone: String, password: String, repeatPassword: String) {
        _fullName.value = fullName
        _email.value = email
        _phone.value = phone
        _password.value = password
        _repeatPassword.value = repeatPassword
        _registerEnable.value = isValidRegisterForm(fullName, email, phone, password, repeatPassword)
    }

    fun onRegisterClick() {
        if(isValidRegisterForm(fullName.value!!, email.value!!, phone.value!!, password.value!!, repeatPassword.value!!)) {
            _event.postValue(RegisterEvent.Loading)
            val req = RegisterRequest(
                firstName = fullName.value!!.split(" ")[0],
                lastName = fullName.value!!.split(" ")[1],
                birthDate = "1990-01-01",
                email = email.value!!,
                password = password.value!!
            )

            // TODO: manejar los errores de la petición
            viewModelScope.launch {
                try {
                    val response: Response<RegisterResponse> = apiService.registerUser(req)
                    if(response.isSuccessful) {
                        _event.postValue(RegisterEvent.RegisterSuccess)
                    } else {
                        _event.postValue(RegisterEvent.Error(response.errorBody()?.string() ?: "Error"))
                    }
                } catch (e: Exception) {
                    _event.postValue(RegisterEvent.Error(e.message ?: "Error"))
                }
            }
        }
    }

    private fun isValidRegisterForm(fullName: String, email: String, phone: String, password: String, repeatPassword: String): Boolean {
        return CommonValidations.isValidFullName(fullName) &&
                CommonValidations.isValidEmail(email) &&
                CommonValidations.isValidPhone(phone) &&
                CommonValidations.isValidPassword(password) &&
                CommonValidations.isValidRepeatPassword(password, repeatPassword)
    }
}