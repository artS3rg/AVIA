package com.artinc.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artinc.domain.models.FeedItem
import com.artinc.domain.usecases.GetFeedItemsUseCase
import kotlinx.coroutines.launch

class FeedViewModel(private val getFeedItems: GetFeedItemsUseCase) : ViewModel() {
    private val _items = MutableLiveData<List<FeedItem>>()
    val items: LiveData<List<FeedItem>> get() = _items

    fun getFeed() {
        viewModelScope.launch {
            _items.value = getFeedItems()
        }
    }
}