package com.artinc.domain.models

data class AirItem (
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: Price
)