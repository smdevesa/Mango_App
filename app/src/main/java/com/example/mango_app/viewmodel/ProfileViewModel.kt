package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.ui.screen.ProfileEvent
import kotlinx.coroutines.launch

class ProfileViewModel(private val apiService: ApiService) : ViewModel() {

    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _birthDate = MutableLiveData<String>()
    val birthDate: LiveData<String> = _birthDate

    private val _event = MutableLiveData<ProfileEvent>()
    val event: LiveData<ProfileEvent> = _event

    fun loadProfile(){
        _event.postValue(ProfileEvent.Loading)
        viewModelScope.launch {
            try {
                val response = apiService.getUserInfo()
                if(response.isSuccessful) {
                    val profile = response.body()
                    _firstName.postValue(profile?.firstName)
                    _lastName.postValue(profile?.lastName)
                    _fullName.postValue("${profile?.firstName} ${profile?.lastName}")

                    _email.postValue(profile?.email)
                    _birthDate.postValue("HARDCODEADO")

                    _event.postValue(ProfileEvent.None)
                } else {
                    //TODO Handle error
                    _event.postValue(ProfileEvent.None)
                }
            } catch (e: Exception) {
                //TODO Handle error
                _event.postValue(ProfileEvent.None)
            }
        }
    }


    fun onLogoutClick() {
        _event.postValue(ProfileEvent.Loading)
        viewModelScope.launch {
            try {
                val response = apiService.logout()
                if(response.isSuccessful) {
                    _event.postValue(ProfileEvent.Logout)
                } else {
                    //TODO Handle error
                    _event.postValue(ProfileEvent.None)
                }
            } catch (e: Exception) {
                //TODO Handle error
                _event.postValue(ProfileEvent.None)
            }
        }

    }

}