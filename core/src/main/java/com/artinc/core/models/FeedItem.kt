package com.artinc.core.models

data class FeedItem(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)