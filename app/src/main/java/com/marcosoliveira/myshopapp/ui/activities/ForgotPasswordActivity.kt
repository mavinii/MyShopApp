package com.marcosoliveira.myshopapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.makeText
import com.google.android.material.snackbar.Snackbar.*
import com.google.firebase.auth.FirebaseAuth
import com.marcosoliveira.myshopapp.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        recoveryAccess()
    }

    // Function that recovery an user account
    private fun recoveryAccess(){

        val btnSubmit = findViewById<Button>(R.id.btn_submit)
        val etEmail = findViewById<EditText>(R.id.et_email_forgot_password)

        btnSubmit.setOnClickListener {

            // It cuts all the empty spaces in the email input
            val email = etEmail.text.toString().trim() {it <= ' '}

            // Checking if the input email is empty
            if (email.isEmpty()){
                make(etEmail, "EMAIL can not be empty!", LENGTH_LONG).show()
            }
            else {

//                // it calls the class Loading to display to progressBar animation
//                progressDialog.show(this,"")
//
//                // progressBar animation timer
//                Handler(Looper.getMainLooper()).postDelayed({
//                    // Dismiss progress bar after 4 seconds
//                    progressDialog.dialog.dismiss()
//                }, 3000)

            // Firebase Function to reset the mail
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        // Message
                        makeText(this@ForgotPasswordActivity, "Email sent! Please check your email.",
                            Toast.LENGTH_LONG
                        ).show()

                        // It finishes the screen and back to the login
                            finish()
                    } else {
                        //It show a message in case something goes wrong
                        makeText(
                            this@ForgotPasswordActivity,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }




}