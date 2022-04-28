package com.marcosoliveira.myshopapp.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.marcosoliveira.myshopapp.R

class CheckoutActivity : AppCompatActivity() {
                                            // , PaymentResultListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)


        val btclickme = findViewById<Button>(R.id.btclickme)
        btclickme.setOnClickListener {
            validatePaymentDetails()
        }
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
                orderCompletedWithSuccess()
//                make(etLastName, "Order completed!", Snackbar.LENGTH_LONG).show()
                true
            }

        }

    }

    // Order completed
    fun orderCompletedWithSuccess(){
        Toast.makeText(this@CheckoutActivity, "Order completed successfully!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
        startActivity(intent)

        // It takes the user to the login page and close the register screen
        finish()
    }


}