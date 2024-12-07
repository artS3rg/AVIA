package com.artinc.network.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.core.models.TicketItem
import com.artinc.network.networks.TicketsApiService
import kotlinx.coroutines.launch

class TicketsViewModel(private val ticketsService: TicketsApiService) : ViewModel() {
    val ticketsLiveData: MutableLiveData<List<TicketItem>> = MutableLiveData()

    fun getTickets() {
        viewModelScope.launch {
            val response = ticketsService.getTickets()
            ticketsLiveData.postValue(response.tickets)
        }
    }
}