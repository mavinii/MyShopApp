package com.marcosoliveira.myshopapp.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.models.User
import com.marcosoliveira.myshopapp.util.Constants
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_register.*

class UserProfileActivity : BaseActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    private val currentUser = FirebaseAuth.getInstance().currentUser

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        firebaseAuth = FirebaseAuth.getInstance()

        // getting this object from LoginActivity
        var userDetails: User = User()
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)){
            // it gets the details from intent as a ParcelableExtra
            userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        // It displays the user name and email of the user profile, from the db,
        // But it still has some bugs, once it displays the user details, going to the main activity
        // and coming back, the details disappear
        user_profile_name.text = userDetails.firstName
        user_profile_email.text = userDetails.email
        user_profile_number.text = userDetails.phone

        val backBtn = findViewById<ImageView>(R.id.toolbar_icon_user_profile)
        backBtn.setOnClickListener {
            onBackPressed()
        }

        // It disables the log out button, until the user connects
        val btnLogOut = findViewById<Button>(R.id.log_out_user_profile)
        if (currentUser != null) {
            btnLogOut.setOnClickListener {
                logInOrLogOut()
            }
        } else {
            btnLogOut.isEnabled = false
            btnLogOut.setBackgroundColor(ContextCompat.getColor(btnLogOut.context,R.color.disableBtn))
        }
    }

    // this function logs out the user
    fun logInOrLogOut(){

            firebaseAuth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"You are logged out!", Toast.LENGTH_LONG).show()
    }
}