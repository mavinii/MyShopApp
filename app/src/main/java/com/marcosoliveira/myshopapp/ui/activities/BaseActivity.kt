package com.marcosoliveira.myshopapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcosoliveira.myshopapp.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}