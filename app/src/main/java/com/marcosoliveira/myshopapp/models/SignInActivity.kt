package com.marcosoliveira.myshopapp.models

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.marcosoliveira.myshopapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.marcosoliveira.myshopapp.firestore.FirestoreClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

// 22931 - Marcos Oliveira
open class SignInActivity : SignupActivity(), View.OnClickListener {

//    lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_authentication)

//        auth = FirebaseAuth.getInstance()

        // FORGOT PASSWORD
        val tvForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
        tvForgotPassword.setOnClickListener(this)

        // LOGIN BUTTON
        val signInbt = findViewById<Button>(R.id.signInbt)
        signInbt.setOnClickListener(this)
//        val signInbt = findViewById<Button>(R.id.signInbt)
//        signInbt.setOnClickListener {
//            LogInuser()
//        }

        // REGISTER
        val signupbt = findViewById<TextView>(R.id.signupbt)
        signupbt.setOnClickListener(this)
    //        val signupbt = findViewById<TextView>(R.id.signupbt)
//        signupbt.setOnClickListener {
//            val intent1= Intent(this, SignupActivity::class.java)
//            startActivity(intent1)
//        }
    }

    // This function takes the user to fill in the checkout screen:
    fun userLoggedInSuccess(user: User){

        // it prints the user details
        Log.i("First Name: ", user.firstName)
        Log.i("Last Name: ", user.lastName)
        Log.i("Email: ", user.email)

        // Redirect the user to the Main Screen after log in
        startActivity(Intent(this@SignInActivity, CheckoutActivity::class.java))
        finish()
    }

    override fun onClick(view: View?){
        if (view != null){
            when (view.id){

                R.id.tv_forgot_password -> {
                    Toast.makeText(this, "Forgot password is not working yet", Toast.LENGTH_LONG).show()
//                    startActivity(Intent(this@SignInActivity, CheckoutActivity::class.java))
                }

                R.id.signInbt -> {
                    logInRegisteredUser()
                }

                R.id.signupbt -> {
                    val intent = Intent(this@SignInActivity, SignupActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    // This function validates if user typed email and password correctly
    private fun validateLoginDetails(): Boolean {

        val email = findViewById<EditText>(R.id.EmailText)
        val password = findViewById<EditText>(R.id.passwordText)

        return when {
            TextUtils.isEmpty(email.text.toString().trim() {it <= ' '}) -> {
                Toast.makeText(this, "EMAIL can not be empty!", Toast.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(password.text.toString().trim() {it <= ' '}) -> {
                Toast.makeText(this, "PASSWORD can not be empty!", Toast.LENGTH_LONG).show()
                false
            }
            else -> {
//                Snackbar.make(etPassword, "", Snackbar.LENGTH_LONG).show()
                true
            }
        }
    }

    // This function is called if the user is registered on database
    private fun logInRegisteredUser(){
        if (validateLoginDetails()){

            // trim cuts all the empty spaces
            val email = findViewById<EditText>(R.id.EmailText).text.toString().trim { it <= ' '}
            val password = findViewById<EditText>(R.id.passwordText).text.toString().trim { it <= ' '}

            // It connects to the database and log in the user
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)

                .addOnCompleteListener { task ->

                    // If user is registered on database, he can login
                    if(task.isSuccessful){
                        FirestoreClass().getUserDetails(this@SignInActivity)
                    } else {

                        //It show a message in case something goes wrong
                        makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }


//    private fun LogInuser(){
//
//        val email = findViewById<EditText>(R.id.EmailText).text.toString()
//        val password = findViewById<EditText>(R.id.passwordText).text.toString()
//
////        val email=EmailText.text.toString()
////        val password=passwordText.text.toString()
//
//        //var state:Boolean=true
//
//        if(email.isNotEmpty() && password.isNotEmpty()){
//            CoroutineScope(Dispatchers.IO).launch {
//                try {
//                    auth.signInWithEmailAndPassword(email,password).await()
//                    withContext(Dispatchers.Main) {
//                        currentlyLoggedIn()
//                    }
//                }
//                catch (e: FirebaseAuthInvalidUserException){
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@SignInActivity, "Invalid email", Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//                catch (e: FirebaseAuthInvalidCredentialsException){
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@SignInActivity, "Invalid password", Toast.LENGTH_SHORT).show()
//
//                    }
//
//                }
//                catch (e: Exception) {
//                    withContext(Dispatchers.Main){
//                        Toast.makeText(this@SignInActivity,e.message, Toast.LENGTH_SHORT).show()
//
//                    }
//                }
//            }
//        }
//        else if(email.isEmpty()){
//            Toast.makeText(this@SignInActivity,"Please enter your email", Toast.LENGTH_SHORT).show()
//
//        }
//        else if(password.isEmpty()){
//            Toast.makeText(this@SignInActivity,"Please enter your password", Toast.LENGTH_SHORT).show()
//
//        }
//
//    }

//    private fun currentlyLoggedIn(){
//        val user=auth.currentUser
//
//        if(user!=null){
//            val intent= Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        currentlyLoggedIn()
//    }

}