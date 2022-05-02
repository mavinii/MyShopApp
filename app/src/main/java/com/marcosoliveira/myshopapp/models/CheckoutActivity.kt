package com.marcosoliveira.myshopapp.models

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import com.marcosoliveira.myshopapp.util.Constants


class CheckoutActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val btnPayNow = findViewById<Button>(R.id.btn_pay_now)
        btnPayNow.setOnClickListener {
            savingUserPaymentDetails()

        }
        // install Trigger email extension? to send email once paid? youtube?
    }

    // It checks if all inputs are valid before finalizing the payment
    private fun validatePaymentDetails(): Boolean{

        val phoneNo = findViewById<TextView>(R.id.etPhone)
        val address = findViewById<TextView>(R.id.etAddress)
        val city = findViewById<TextView>(R.id.etCity)
        val state = findViewById<TextView>(R.id.etState)
        val zipCode = findViewById<TextView>(R.id.etZipCode)

        // It checks if all condition is valid
        return when {
            TextUtils.isEmpty(phoneNo.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(phoneNo, "PHONE can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(address.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(address, "ADDRESS can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(city.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(city, "CITY can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(state.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(state, "STATE can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(zipCode.text.toString().trim() {it <= ' '}) -> {
                Snackbar.make(zipCode, "ZIP can not be empty!", Snackbar.LENGTH_LONG).show()
                false
            }

            else -> {
//                orderCompletedWithSuccess()
//                make(etLastName, "Order completed!", Snackbar.LENGTH_LONG).show()
                true
            }
        }

    }

    // https://www.youtube.com/watch?v=MgY8P6j9-Og
    // https://firebase.google.com/docs/auth/android/manage-users
    // SHOWING USERNAME: https://www.youtube.com/watch?v=5UEdyUFi_uQ
    fun savingUserPaymentDetails() {

        if (validatePaymentDetails()){

            // getting all the user details to be saved on database
            val phoneNo = findViewById<TextView>(R.id.etPhone).text.toString().trim { it <= ' '}
            val address = findViewById<TextView>(R.id.etAddress).text.toString().trim { it <= ' '}
            val city = findViewById<TextView>(R.id.etCity).text.toString().trim { it <= ' '}
            val state = findViewById<TextView>(R.id.etState).text.toString().trim { it <= ' '}
            val zipCode = findViewById<TextView>(R.id.etZipCode).text.toString().trim { it <= ' '}

            // it sends the user details to database associated his ID
            val data = hashMapOf(
                "phone" to phoneNo,
                "address" to address,
                "city" to city,
                "state" to state,
                "zipCode" to zipCode
            )
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                db.collection("user").document(currentUser.uid)
                    .set(data, SetOptions.merge())
            }
            orderCompletedWithSuccess()
        }

    }

    // Order completed
    fun orderCompletedWithSuccess(){
        Toast.makeText(this@CheckoutActivity, "Order completed! Please check your E-mail.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
        startActivity(intent)

        // It takes the user to the login page and close the register screen
        finish()
    }


}