package com.marcosoliveira.myshopapp.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.marcosoliveira.myshopapp.R

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val userName = findViewById<TextView>(R.id.user_profile_name)
//        val user = Firebase.auth.currentUser
//
//        if (user != null){
//            userName.text = user.displayName
//        } else{
//            userName.text = "Hey anonymous"
//        }



    }
}