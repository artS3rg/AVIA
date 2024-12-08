package com.artinc.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.domain.models.AirItem
import com.artinc.data.apis.AirApiService
import com.artinc.domain.usecases.GetAirItemsUseCase
import kotlinx.coroutines.launch

class AirViewModel(private val getAirItems: GetAirItemsUseCase) : ViewModel() {
    private val _items = MutableLiveData<List<AirItem>>()
    val items: LiveData<List<AirItem>> get() = _items

    fun getAir() {
        viewModelScope.launch {
            _items.value = getAirItems()
        }
    }
}