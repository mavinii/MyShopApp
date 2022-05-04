package com.marcosoliveira.myshopapp.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.models.User
import com.marcosoliveira.myshopapp.util.Constants
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var userDetails: User = User()
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)){

            // it gets the details from intent as a ParcelableExtra
            userDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        // It displays the user name and email of the user profile, from the db
        user_profile_name.text = userDetails.firstName
        user_profile_email.text = userDetails.email
    }

    // This function should be able to get the user pick 4:40
//    override fun onClick(v: View?){
//      Cod goes here
//    }
}