package com.marcosoliveira.myshopapp.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import com.marcosoliveira.myshopapp.R
import com.google.firebase.auth.FirebaseAuth
import com.marcosoliveira.myshopapp.firestore.FirestoreClass
import com.marcosoliveira.myshopapp.models.User
import com.marcosoliveira.myshopapp.util.Constants

// 22931 - Marcos Oliveira
open class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // this hides the very top bar
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // FORGOT PASSWORD
        val tvForgotPassword = findViewById<TextView>(R.id.tv_forgot_password)
        tvForgotPassword.setOnClickListener(this)

        // LOGIN BUTTON
        val signInbt = findViewById<Button>(R.id.signInbt)
        signInbt.setOnClickListener(this)

        // REGISTER
        val signupbt = findViewById<TextView>(R.id.signupbt)
        signupbt.setOnClickListener(this)
    }

    // This function takes the user to fill in the checkout screen:
    fun userLoggedInSuccess(user: User){

        // it prints the user details in the log
        Log.i("First Name: ", user.firstName!!)
        Log.i("Last Phone: ", user.phone!!)
        Log.i("Last Name: ", user.email!!)

        // TODO: later on i can add a "if" case the user had already entered their info,
        // TODO: take them to the payment screen without take the user to the delivery screen. 4:12:00
        if (user.state != null){
            // If the user profile is incomplete then launch the UserProfileActivity.
            val intent = Intent( this@LoginActivity, UserProfileActivity::class.java)
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        }else{
            // Redirect the user to Main Screen after log in.
            startActivity(Intent(this@LoginActivity, DeliveryActivity::class.java))
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
        }
        finish()
    }

    override fun onClick(view: View?){
        if (view != null){
            when (view.id){

                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }

                R.id.signInbt -> {
                    logInRegisteredUser()
                }

                R.id.signupbt -> {
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
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
                makeText(this, "EMAIL can not be empty!", Toast.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(password.text.toString().trim() {it <= ' '}) -> {
                makeText(this, "PASSWORD can not be empty!", Toast.LENGTH_LONG).show()
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

            // shows the progressbar
            showProgressDialog(resources.getString(R.string.please_wait))

            // trim cuts all the empty spaces
            val email = findViewById<EditText>(R.id.EmailText).text.toString().trim { it <= ' '}
            val password = findViewById<EditText>(R.id.passwordText).text.toString().trim { it <= ' '}

            // It connects to the database and log in the user
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)

                .addOnCompleteListener { task ->

                    // Hide de progress dialog
                    hideProgressDialog()

                    // If user is registered on database, he can login
                    if(task.isSuccessful){
                        FirestoreClass().getUserDetails(this@LoginActivity)
                    } else {
                        //It show a message in case something goes wrong
                        makeText(this, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}