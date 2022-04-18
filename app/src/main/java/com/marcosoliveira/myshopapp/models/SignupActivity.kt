package com.marcosoliveira.myshopapp.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marcosoliveira.myshopapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignupActivity : AppCompatActivity() {

    lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_signup)

        auth= FirebaseAuth.getInstance()

        val registerbt = findViewById<Button>(R.id.registerbt)
        registerbt.setOnClickListener {
            registeruser()
        }
    }


    private fun registeruser(){

        val email = findViewById<EditText>(R.id.EmailText).text.toString()
        val password = findViewById<EditText>(R.id.passwordText).text.toString()

//        val email=signemailtv.text.toString()
//        val password=signpasstv.text.toString()



        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main) {
                        currentlyLoggedIn()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SignupActivity,e.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }

    private fun currentlyLoggedIn(){
        val user=auth.currentUser

        if(user != null){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}