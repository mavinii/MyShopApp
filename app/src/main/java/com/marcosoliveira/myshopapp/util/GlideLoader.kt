package com.marcosoliveira.myshopapp.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.marcosoliveira.myshopapp.R
import java.io.IOException
import java.net.URI

// this class is going to load the user pic
class GlideLoader(val context: Context) {

    fun loadUserPicture(imgeURI: Uri, imageView: ImageView){
        try {
            // Glide load the user image in the ImageView
            Glide
                .with(context)
                .load(imgeURI)                                  // URI of the image
                .centerCrop()                                   // Scale type of the image
                .placeholder(R.drawable.ic_launcher_background) // A default place holder if img is failed to load
                .into(imageView)                                // the view in which the image will be loaded
        } catch (e: IOException){
            e.printStackTrace()
        }
    }
}