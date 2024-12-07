package com.artinc.core.models

data class TicketItem (
    val id: Int,
    val badge: String?,
    val price: Price,
    val provider_name: String,
    val company: String,
    val departure: Place,
    val arrival: Place,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val luggage: Luggage,
    val hand_luggage: HandLuggage,
    val is_returnable: Boolean,
    val is_exchangable: Boolean,
)

data class Place (
    val town: String,
    val date: String,
    val airport: String,
)

data class Luggage (
    val has_luggage: Boolean,
    val price: Price?,
)

data class HandLuggage (
    val has_hand_luggage: Boolean,
    val size: String,
)