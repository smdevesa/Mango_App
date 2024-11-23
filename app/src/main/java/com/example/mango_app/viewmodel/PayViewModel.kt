package com.example.mango_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.data.Card
import com.example.mango_app.model.data.PayCardRequest
import com.example.mango_app.model.data.PayLinkBalanceRequest
import com.example.mango_app.model.data.RechargeRequest
import kotlinx.coroutines.launch

class PayViewModel(private val apiService: ApiService) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    private val _amount = MutableLiveData<String>()
    val amount: LiveData<String> = _amount

    private val _successMessageVisible = MutableLiveData(false)
    val successMessageVisible: LiveData<Boolean> = _successMessageVisible

    private val _isLinkValid = MutableLiveData<Boolean>()
    val isLinkValid: LiveData<Boolean> = _isLinkValid

    fun validatePaymentLink(linkUuid: String) {
        viewModelScope.launch {
            try {
                // Llama a un endpoint para validar el link (ajusta según tu API)
                val response = apiService.getPaymentDetails(linkUuid)
                _isLinkValid.value = response.isSuccessful
                if(response.isSuccessful) {
                    _successMessageVisible.value = true
                }
            } catch (e: Exception) {
                Log.e("PayViewModel", "Error validando el link: ${e.message}")
                _isLinkValid.value = false
            }
        }
    }
    suspend fun fetchPaymentDetails(linkUuid: String) {
        try {
            // Llama al endpoint de la API para obtener los detalles del pago
            val response = apiService.getPaymentDetails(linkUuid)

            if (response.isSuccessful) {
                // Extrae el monto del cuerpo de la respuesta
                val paymentDetails = response.body()
                val amount = paymentDetails?.payment?.amount

                // Actualiza el LiveData con el monto
                _amount.value = amount?.toString()
            } else {
                // Manejo de errores de la API
                Log.e("PayViewModel", "Error al obtener detalles del pago: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            // Manejo de excepciones (red, tiempo de espera, etc.)
            Log.e("PayViewModel", "Excepción al obtener detalles del pago: ${e.message}")
        }
    }

    fun payWithBalance(linkUuid: String) {
        viewModelScope.launch {
            try {
                // Cuerpo de la solicitud para el pago con balance
                val requestBody = PayLinkBalanceRequest()

                // Llamada a la API
                val response = apiService.paymentBalanceWithLink(linkUuid, requestBody)

                if (response.isSuccessful && response.body()?.success == true) {
                    // Actualiza el estado para reflejar éxito
                    _successMessageVisible.postValue(true)

                } else {
                    // Registra el error y asegura que el estado no refleje éxito
                    Log.e("PayViewModel", "Error en payWithBalance: ${response.errorBody()?.string()}")
                    _successMessageVisible.postValue(false)
                }
            } catch (e: Exception) {
                // Registra la excepción y asegura que el estado no refleje éxito
                Log.e("PayViewModel", "Excepción en payWithBalance: ${e.message}")
                _successMessageVisible.postValue(false)
            }
        }
    }

    fun payWithCard(linkUuid: String, cardId: Int) {
        viewModelScope.launch {
            try {
                // Cuerpo de la solicitud para el pago con tarjeta
                val requestBody = PayCardRequest(cardId = cardId)

                // Llamada a la API
                val response = apiService.paymentCardWithLink(linkUuid, requestBody)

                if (response.isSuccessful && response.body()?.success == true) {
                    // Actualiza el estado para reflejar éxito
                    _successMessageVisible.postValue(true)
                } else {
                    // Registra el error y asegura que el estado no refleje éxito
                    Log.e("PayViewModel", "Error en payWithCard: ${response.errorBody()?.string()}")
                    _successMessageVisible.postValue(false)
                }
            } catch (e: Exception) {
                // Registra la excepción y asegura que el estado no refleje éxito
                Log.e("PayViewModel", "Excepción en payWithCard: ${e.message}")
                _successMessageVisible.postValue(false)
            }
        }
    }
}