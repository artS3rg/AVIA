package com.artinc.domain.models

data class FeedItem(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)