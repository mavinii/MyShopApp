package com.marcosoliveira.myshopapp.models

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.marcosoliveira.myshopapp.R


class CheckoutActivity : AppCompatActivity() {

    lateinit var user: User
    lateinit var auth: FirebaseAuth
    var db = Firebase.firestore

    // Notification
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        createNotificationChannel()

        // btn pay now sends a message once the user paid for the product
        val btnPayNow = findViewById<Button>(R.id.btn_pay_now)
        btnPayNow.setOnClickListener {
            savingUserPaymentDetails()
        }
    }

    //It creates a notification
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
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
                Toast.makeText(this, "PHONE can not be empty!", Toast.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(address.text.toString().trim() {it <= ' '}) -> {
                Toast.makeText(this, "ADDRESS can not be empty!", Toast.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(city.text.toString().trim() {it <= ' '}) -> {
                Toast.makeText(this, "CITY can not be empty!", Toast.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(state.text.toString().trim() {it <= ' '}) -> {
                Toast.makeText(this, "STATE can not be empty!", Toast.LENGTH_LONG).show()
                false
            }
            TextUtils.isEmpty(zipCode.text.toString().trim() {it <= ' '}) -> {
                Toast.makeText(this, "ZIP can not be empty!", Toast.LENGTH_LONG).show()
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

            // it sends the user details to database associated with his/her ID
            val data = hashMapOf(
                "phone"     to phoneNo,
                "address"   to address,
                "city"      to city,
                "state"     to state,
                "zipCode"   to zipCode
            )
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                db.collection("user").document(currentUser.uid)
                    .set(data, SetOptions.merge())
            }

            // Notification after all inputs have been filled in
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle("Payment confirmation.")
                .setContentText("Thanks for buying with us.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            val notificationManager = NotificationManagerCompat.from(this)
            notificationManager.notify(NOTIFICATION_ID, notification)

            orderCompletedWithSuccess()
        }
    }

    // Order completed
    fun orderCompletedWithSuccess(){

        // TODO Send the user their profile after payment
//        Toast.makeText(this@CheckoutActivity, "Order completed! Please check your E-mail.", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@CheckoutActivity, MainActivity::class.java)
        startActivity(intent)

        // It takes the user to the login page and close the register screen
        finish()
    }

}