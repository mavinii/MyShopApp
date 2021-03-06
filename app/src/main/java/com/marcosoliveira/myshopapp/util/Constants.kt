package com.marcosoliveira.myshopapp.util

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object Constants {
    // LOCAL variables
    const val USERS: String = "users"
    const val MYSHOPAPP_PREFERENCES: String = "MyShopAppPreferences"
    const val LOGGED_IN_USERNAME: String = "logged_in_username"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"

    // THE CODE 2, shows that user gave permission to use storage
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1


    // this function helps to get the user picture from the device gallery
    fun showImageChooser(activity: Activity){

        // this intent for launching the image selection of phone storage
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )

        // lunches the image selection of phone storage using constant code
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }
}