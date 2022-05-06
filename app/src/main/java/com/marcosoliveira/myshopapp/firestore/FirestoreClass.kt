package com.marcosoliveira.myshopapp.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.marcosoliveira.myshopapp.ui.activities.LoginActivity
import com.marcosoliveira.myshopapp.ui.activities.RegisterActivity
import com.marcosoliveira.myshopapp.models.User
import com.marcosoliveira.myshopapp.util.Constants

// Class responsible for taking care of all the Firestorm data
// 22931 - Marcos Oliveira
class FirestoreClass {

    // Associating database to a var mFirestore
    private val mFirestore = FirebaseFirestore.getInstance()


    // This function register an user, from the RegisterActivity
    fun registerUser(activity: RegisterActivity, userInfo: User){
        mFirestore.collection(Constants.USER)
            .document(userInfo.id)                  // It gets the id from "User" activity
            .set(userInfo, SetOptions.merge())      // It merges the both information
            .addOnFailureListener {
                activity.userRegistrationSuccess()  // It calls my function userRegistrationonSuccess from RegisterActivity class
            }

            .addOnFailureListener{
                Log.e(activity.javaClass.simpleName, "ERROR WHILE REGISTERING THE USER")
            }
    }

    // This function is retrieving data from the user
    fun getCurrentUserID(): String {

        //An instance of the current user
        val currentUser = FirebaseAuth.getInstance().currentUser

        // A variable to assign the currentUserID, if it is not null
        var currentUserID = ""
        if (currentUser != null){
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    // Function that gets the user details
    fun getUserDetails(activity: Activity){

        // Passing the collection name with the data we want
        mFirestore.collection(Constants.USER)

            // The document if to get the fields of an user
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.toString())

                // I have no idea what it is doing
                val user = document.toObject(User::class.java)!!


                // to retrieve data from firebase and display on UserProfileActivity
                val sharedPreferences = activity.getSharedPreferences(Constants.MYSHOPAPP_PREFERENCES, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(Constants.LOGGED_IN_USERNAME, "${user.firstName}")
                editor.putString(Constants.LOGGED_IN_EMAIL, "${user.phone}")
                editor.apply()


                // START
                when(activity){
                    is LoginActivity -> {
                        // Call a function of base activity for transferring the result to it
                        // userLoggedInSuccess is a function from LoginActivity
                        activity.userLoggedInSuccess(user)
                    }
                }
                // END
            }

            // Case something goes wrong
            .addOnFailureListener { e ->
                // It shows any error in the log
                Log.e(
                    activity.javaClass.simpleName, "Error while getting user details", e
                )
            }
    }
}



















