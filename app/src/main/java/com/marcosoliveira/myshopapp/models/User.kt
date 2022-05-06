package com.marcosoliveira.myshopapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// all inf that is going to be stored on Firebase
@Parcelize
data class User (
    val id: String = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val phone: String? = "",
    val address: String? = "",
    val city: String? = "",
    val state: String? = "",
    val zipCode: String? = ""
): Parcelable