package com.marcosoliveira.myshopapp.ui.activities

import android.Manifest
import android.Manifest.*
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.models.User
import com.marcosoliveira.myshopapp.util.Constants
import com.marcosoliveira.myshopapp.util.GlideLoader
//import kotlinx.android.synthetic.main.activity_user_profile.*
//import kotlinx.android.synthetic.main.activity_register.*
import java.io.IOException

class UserProfileActivity : BaseActivity(), View.OnClickListener {

    private var mUserDetails: User? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        firebaseAuth = FirebaseAuth.getInstance()

        // This is getting the object from LoginActivity
//        var mUserDetails: User = User()
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)){
            // it gets the details from intent as a ParcelableExtra
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        // It displays the user name and email of the user profile, from the db,
        // But it still has some bugs, once it displays the user details, going to the main activity
        // and coming back, the details disappear
//        findViewById<TextView>(R.id.user_profile_Fname).isEnabled = false
        findViewById<TextView>(R.id.user_profile_Fname).text = mUserDetails?.firstName  //USER NAME
        findViewById<TextView>(R.id.user_profile_Lemail).text = mUserDetails?.lastName  //USER PHONE ? .toString()
        findViewById<TextView>(R.id.user_profile_email).text = mUserDetails?.email      //USER EMAIL


        val backBtn = findViewById<ImageView>(R.id.toolbar_icon_user_profile)
        backBtn.setOnClickListener {
            onBackPressed()
        }

        // It disables the log out button and picture, until the user connects
        // to their account, allowing them to upload their profile picture
        val btnLogOut = findViewById<Button>(R.id.log_out_user_profile)
        if (currentUser != null) {

            // it allows the user to click in their photo
            val userImg = findViewById<ImageView>(R.id.user_img)
            userImg.setOnClickListener(this@UserProfileActivity)

            btnLogOut.setOnClickListener {
                logInOrLogOut()
            }
        } else {
            val userImg = findViewById<ImageView>(R.id.user_img)
            userImg.isEnabled = false
            btnLogOut.isEnabled = false
            btnLogOut.setBackgroundColor(ContextCompat.getColor(btnLogOut.context,R.color.disableBtn))
        }
    }

    // this function logs out the user
    fun logInOrLogOut(){

//        showProgressDialog()

        firebaseAuth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this,"You are logged out!", Toast.LENGTH_LONG).show()
    }

    // Method implemented because im using View.OnClickListener
    // Function that gets the user picture on the gallery
    override fun onClick(view: View?) {
        if (view != null){
            when (view.id) {

                R.id.user_img -> {

                    // If user already gave permission
                    if(checkSelfPermission(this, permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//                        Toast.makeText(this, "You already have storage permission", Toast.LENGTH_LONG).show()
                        Constants.showImageChooser(this)
                    } else {
                        //
                        ActivityCompat.requestPermissions(this, arrayOf(permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE)
                    }
                }
            }
        }
    }

    // Function that gets the user picture on the gallery
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Constants.showImageChooser(this)
//            Toast.makeText(this, "Image added", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "PERMISSION DENIED! You can also allow it on Settings.", Toast.LENGTH_LONG).show()
        }
    }

    // Function that gets the user picture on the gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == Constants.PICK_IMAGE_REQUEST_CODE){
                if (data !== null){
                    try {
                        val selectedImageFileUri = data.data!!

                        val userImg = findViewById<ImageView>(R.id.user_img)
//                      userImg.setImageURI((Uri.parse(selectedImageFileUri.toString())))
                        GlideLoader(this).loadUserPicture(selectedImageFileUri, userImg)
                    } catch (e: IOException){
                        e.printStackTrace()
                        Toast.makeText(this, "Ops, image selected failed!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}