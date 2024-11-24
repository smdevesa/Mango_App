package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.Card
import kotlinx.coroutines.launch
import retrofit2.Response

class CardViewModel (private val apiService: ApiService) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchCards()
    }


    private fun fetchCards() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val response: Response<List<Card>> = apiService.getCards()
                if(response.isSuccessful) {
                    _cards.value = response.body()?.toList()
                } else {
                    //
                }
            }
            catch (e: Exception) {
                //
            }
            finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun deleteCard(id: Int) {
        viewModelScope.launch {
            try {
                val response: Response<Unit> = apiService.deleteCard(id)
                if(response.isSuccessful) {
                    fetchCards()
                } else {
                    //
                }
            } catch (e: Exception) {
                //
            }
        }
    }
}