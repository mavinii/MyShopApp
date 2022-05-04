package com.marcosoliveira.myshopapp.models

// all inf that is going to be stored on Firebase
data class User (
    val id: String = "",
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val address: String? = null,
    val city: String? = null,
    val state: String? = null,
    val zipCode: String? = null
)