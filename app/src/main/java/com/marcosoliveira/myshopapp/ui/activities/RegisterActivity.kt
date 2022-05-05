package com.marcosoliveira.myshopapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.marcosoliveira.myshopapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.marcosoliveira.myshopapp.firestore.FirestoreClass
import com.marcosoliveira.myshopapp.models.User

open class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)

        // It takes the user to the create an account page
        val tvLogin = findViewById<TextView>(R.id.tv_login)
        tvLogin.setOnClickListener {
            onBackPressed()
        }

        val registerbt = findViewById<Button>(R.id.registerbt)
        registerbt.setOnClickListener {
            registerUser()
        }
    }


    // It checks if all input is valid before registering
    private fun validateRegisterDetails(): Boolean {

        val etFirstName = findViewById<EditText>(R.id.et_first_name)
        val etLastName = findViewById<EditText>(R.id.et_last_name)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val etConfirmPassword = findViewById<EditText>(R.id.et_confirm_password)
        val iAgree = findViewById<CheckBox>(R.id.cb_terms_and_condition)

        return when {
            TextUtils.isEmpty(etFirstName.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(etFirstName, "FIRST NAME can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(etLastName.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(etLastName, "LAST NAME can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(etEmail.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(etEmail, "EMAIL can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(etPassword.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(etPassword, "PASSWORD can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }

            TextUtils.isEmpty(etConfirmPassword.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(
                    etConfirmPassword,
                    "CONFIRM PASSWORD can not be empty!",
                    Snackbar.LENGTH_LONG
                ).show()
                false
            }

            !iAgree.isChecked -> {
                Snackbar.make(
                    etConfirmPassword,
                    "Please Accept the Terms and Condition!",
                    Snackbar.LENGTH_LONG
                ).show()
                false
            }

            else -> {
//                make(etLastName, "Thanks for registering!", Snackbar.LENGTH_LONG).show()
                true
            }
        }
    }

    // Connecting the database (FireBase)
    private fun registerUser(){

        // Check with validate entries are valid
        if (validateRegisterDetails()){

            // It cuts all the empty spaces in the email and password input
            val firstName = findViewById<TextView>(R.id.et_first_name).text.toString().trim { it <= ' '}
            val lastName = findViewById<TextView>(R.id.et_last_name).text.toString().trim { it <= ' '}
            val email = findViewById<EditText>(R.id.et_email).text.toString().trim { it <= ' '}
            val password = findViewById<EditText>(R.id.et_password).text.toString().trim { it <= ' '}

            //It creates a user with email and password in my database
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    //If registration is successfully done
                    if (task.isSuccessful){

                        //Firebase registered user
                        val fireBaseUser: FirebaseUser = task.result!!.user!!

                        //This saves all user information to the database
                        val user = User(
                            fireBaseUser.uid,
                            firstName,
                            lastName,
                            email,
                        )

                        // it calls my class FirestoreClass and calls the registerUser
                        FirestoreClass().registerUser(this@RegisterActivity, user)
                        userRegistrationSuccess()

                    } else {
                        //It show a message in case something goes wrong
                        Toast.makeText(this@RegisterActivity, task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun userRegistrationSuccess(){
        Toast.makeText(this@RegisterActivity, "You are registered successfully!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)

        // It takes the user to the login page and close the register screen
        finish()
    }

    // clear information
    // txt_user.setText("")


}