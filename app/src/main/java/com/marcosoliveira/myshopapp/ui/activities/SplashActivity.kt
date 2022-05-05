package com.marcosoliveira.myshopapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.marcosoliveira.myshopapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This Handler calls the MainActivity after a delay of 2300 secs
        Handler(Looper.getMainLooper()).postDelayed({

//            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 2000)
    }
}