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

    // LiveData para los valores de entrada
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

    // LiveData para errores específicos por campo
    private val _fullNameError = MutableLiveData<String?>()
    val fullNameError: LiveData<String?> = _fullNameError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> = _emailError

    private val _phoneError = MutableLiveData<String?>()
    val phoneError: LiveData<String?> = _phoneError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError

    private val _repeatPasswordError = MutableLiveData<String?>()
    val repeatPasswordError: LiveData<String?> = _repeatPasswordError

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

    private val _event = MutableLiveData<RegisterEvent>()
    val event: LiveData<RegisterEvent> = _event

    private val _fullNameTouched = MutableLiveData(false)
    private val _emailTouched = MutableLiveData(false)
    private val _phoneTouched = MutableLiveData(false)
    private val _passwordTouched = MutableLiveData(false)
    private val _repeatPasswordTouched = MutableLiveData(false)

    fun onRegisterChanged(
        fullName: String,
        email: String,
        phone: String,
        password: String,
        repeatPassword: String
    ) {
        _fullName.value = fullName
        _email.value = email
        _phone.value = phone
        _password.value = password
        _repeatPassword.value = repeatPassword

        validateFullName(fullName)
        validateEmail(email)
        validatePhone(phone)
        validatePassword(password)
        validateRepeatPassword(password, repeatPassword)

        _registerEnable.value = validateAllFields()
    }


    private fun validateFullName(fullName: String) {
        if (_fullNameTouched.value == true) {
            _fullNameError.value = if (CommonValidations.isValidFullName(fullName)) null else "Nombre completo no válido"
        }
    }

    private fun validateEmail(email: String) {
        if (_emailTouched.value == true) {
            _emailError.value = if (CommonValidations.isValidEmail(email)) null else "Correo electrónico no válido"
        }
    }

    private fun validatePhone(phone: String) {
        if (_phoneTouched.value == true) {
            _phoneError.value = if (CommonValidations.isValidPhone(phone)) null else "Teléfono no válido"
        }
    }

    private fun validatePassword(password: String) {
        if (_passwordTouched.value == true) {
            _passwordError.value = if (CommonValidations.isValidPassword(password)) null else "Contraseña demasiado corta"
        }
    }

    private fun validateRepeatPassword(password: String, repeatPassword: String) {
        if (_repeatPasswordTouched.value == true) {
            _repeatPasswordError.value = if (CommonValidations.isValidRepeatPassword(password, repeatPassword)) null else "Las contraseñas no coinciden"
        }
    }

    private fun validateAllFields(): Boolean {
        return _fullNameError.value == null && _fullName.value?.isNotEmpty() == true &&
                _emailError.value == null && _email.value?.isNotEmpty() == true &&
                _phoneError.value == null && _phone.value?.isNotEmpty() == true &&
                _passwordError.value == null && _password.value?.isNotEmpty() == true &&
                _repeatPasswordError.value == null && _repeatPassword.value?.isNotEmpty() == true
    }

    fun onFieldTouched(field: String) {
        when (field) {
            "fullName" -> _fullNameTouched.value = true
            "email" -> _emailTouched.value = true
            "phone" -> _phoneTouched.value = true
            "password" -> _passwordTouched.value = true
            "repeatPassword" -> _repeatPasswordTouched.value = true
        }
    }



    fun onRegisterClick() {
        if (validateFields()) {
            _event.postValue(RegisterEvent.Loading)
            val req = RegisterRequest(
                firstName = fullName.value!!.split(" ")[0],
                lastName = fullName.value!!.split(" ")[1],
                birthDate = "1990-01-01",
                email = email.value!!,
                password = password.value!!
            )

            viewModelScope.launch {
                try {
                    val response: Response<RegisterResponse> = apiService.registerUser(req)
                    if (response.isSuccessful) {
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

    private fun validateFields(): Boolean {
        // Verifica si ya se validaron los errores previamente
        val allFieldsValid = validateAllFields()

        // Actualiza la habilitación del botón de registro
        _registerEnable.value = allFieldsValid

        return allFieldsValid
    }

}