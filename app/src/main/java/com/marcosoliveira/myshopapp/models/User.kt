package com.marcosoliveira.myshopapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// all inf that is going to be stored on Firebase
// Parcelize can send some information to the UserActivity
@Parcelize
data class User (
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "+353 ",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val profileCompleted: Int = 0
): Parcelable