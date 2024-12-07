package com.artinc.network.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.core.models.AirItem
import com.artinc.network.networks.AirApiService
import kotlinx.coroutines.launch

class AirViewModel(private val airService: AirApiService) : ViewModel() {
    val airLiveData: MutableLiveData<List<AirItem>> = MutableLiveData()

    fun getAir() {
        viewModelScope.launch {
            val response = airService.getAir()
            airLiveData.postValue(response.tickets_offers)
        }
    }
}