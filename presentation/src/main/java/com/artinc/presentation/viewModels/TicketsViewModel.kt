package com.artinc.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.domain.models.TicketItem
import com.artinc.domain.usecases.GetTicketsItemsUseCase
import kotlinx.coroutines.launch

class TicketsViewModel(private val getTicketsItems: GetTicketsItemsUseCase) : ViewModel() {
    private val _items = MutableLiveData<List<TicketItem>>()
    val items: LiveData<List<TicketItem>> get() = _items

    fun getTickets() {
        viewModelScope.launch {
            _items.value = getTicketsItems()
        }
    }
}