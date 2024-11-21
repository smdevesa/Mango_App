package com.example.mango_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.UserDataStore
import com.example.mango_app.model.data.Card
import com.example.mango_app.model.data.GetCardResponse
import com.example.mango_app.model.data.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class CardViewModel (private val apiService: ApiService, private val userDataStore: UserDataStore) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    init {
        fetchCards()
    }


    private fun fetchCards() {
        viewModelScope.launch {
            try {
                val response: Response<GetCardResponse> = apiService.getCards()
                _cards.value = response.body()?.cards
            }
            catch (e: Exception) {
                //TODO Handle error
            }
        }
    }

    fun deleteCard(id: Int) {
        viewModelScope.launch {
            try {
                val response: Response<String> = apiService.deleteCard(id)
                if(response.isSuccessful) {
                    fetchCards()
                } else {
                    //TODO Handle error
                }
            } catch (e: Exception) {
                //TODO Handle error
            }
        }
    }
}