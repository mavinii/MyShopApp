package com.marcosoliveira.myshopapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.models.User
import com.marcosoliveira.myshopapp.util.Constants
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.fragment_register.*

class UserProfileActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        firebaseAuth = FirebaseAuth.getInstance()

//        setupActionBar()

        var userDetails: User = User()
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)){

            // it gets the details from intent as a ParcelableExtra
            userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        // It displays the user name and email of the user profile, from the db
        user_profile_name.text = userDetails.firstName
        user_profile_email.text = userDetails.email
        user_profile_number.text = userDetails.phone

        val btnLogOut = findViewById<Button>(R.id.log_out_user_profile)
        btnLogOut.setOnClickListener {
            logInOrLogOut()
        }
    }

    // this function checks if user is log in, if so, log out the current user
    // if not, takes the user to the log in screen
    fun logInOrLogOut(){

        val currentUser = FirebaseAuth.getInstance().currentUser

        if(currentUser != null){

            firebaseAuth.signOut()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"You are logged out!", Toast.LENGTH_LONG).show()

        } else {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"Please log in or create an account!", Toast.LENGTH_LONG).show()
        }
    }
}