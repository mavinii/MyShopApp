package com.marcosoliveira.myshopapp.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.marcosoliveira.myshopapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignInActivity : AppCompatActivity() {

    lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_authentication)

        auth= FirebaseAuth.getInstance()

        val signInbt = findViewById<Button>(R.id.signInbt)
        signInbt.setOnClickListener {
            LogInuser()
        }

        val signupbt = findViewById<TextView>(R.id.signupbt)
        signupbt.setOnClickListener {
            val intent1= Intent(this, SignupActivity::class.java)
            startActivity(intent1)
        }

    }

    private fun LogInuser(){

        val email = findViewById<EditText>(R.id.EmailText).text.toString()
        val password = findViewById<EditText>(R.id.passwordText).text.toString()

//        val email=EmailText.text.toString()
//        val password=passwordText.text.toString()

        //var state:Boolean=true

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main) {
                        currentlyLoggedIn()
                    }
                }
                catch (e: FirebaseAuthInvalidUserException){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignInActivity, "Invalid email", Toast.LENGTH_SHORT).show()

                    }
                }
                catch (e: FirebaseAuthInvalidCredentialsException){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignInActivity, "Invalid password", Toast.LENGTH_SHORT).show()

                    }

                }
                catch (e: Exception) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SignInActivity,e.message, Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
        else if(email.isEmpty()){
            Toast.makeText(this@SignInActivity,"Please enter your email", Toast.LENGTH_SHORT).show()

        }
        else if(password.isEmpty()){
            Toast.makeText(this@SignInActivity,"Please enter your password", Toast.LENGTH_SHORT).show()

        }

    }

    private fun currentlyLoggedIn(){
        val user=auth.currentUser

        if(user!=null){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        currentlyLoggedIn()
    }



}