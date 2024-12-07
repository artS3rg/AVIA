package com.artinc.network.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.core.models.FeedItem
import com.artinc.network.networks.FeedApiService
import kotlinx.coroutines.launch

class FeedViewModel(private val feedService: FeedApiService) : ViewModel() {
    val feedLiveData: MutableLiveData<List<FeedItem>> = MutableLiveData()

    fun getFeed() {
        viewModelScope.launch {
            val response = feedService.getFeed()
            feedLiveData.postValue(response.offers)
        }
    }
}