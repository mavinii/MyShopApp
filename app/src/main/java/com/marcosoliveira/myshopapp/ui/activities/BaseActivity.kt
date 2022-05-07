package com.marcosoliveira.myshopapp.ui.activities

import android.app.Dialog
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.marcosoliveira.myshopapp.R
//import kotlinx.android.synthetic.main.dialog_progress.*

open class BaseActivity : AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog

    // Snack bar created for showing error or success
    fun showErrorSnackBar(message: String, errorMessage: Boolean){
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view

        if (errorMessage){
            snackbarView.setBackgroundColor(ContextCompat.getColor(this@BaseActivity, R.color.colorSnackBarError))
        } else {
            snackbarView.setBackgroundColor(ContextCompat.getColor(this@BaseActivity, R.color.colorSnackBarSuccess))
        }
        snackbar.show()
    }

    // Function that shows the Progress Dialog
    fun showProgressDialog(){
        mProgressDialog = Dialog(this)

        // Set the screen content from a layout resource.
        // the resource will be inflated adding all top-level views to the screen
        mProgressDialog.setContentView(R.layout.dialog_progress)

        // AFTER i started to user "parcelize" i cannot get the val directly
        // https://stackoverflow.com/questions/64925126/how-to-use-parcelize-now-that-kotlin-android-extensions-is-being-deprecated
//        val progressText = findViewById<TextView>(R.id.tv_progress_text).text
//        mProgressDialog.setTitle("Please, wait...")
//        mProgressDialog.progressText = text OLD

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        // Start the dialog and display it on the screen
        mProgressDialog.show()
    }

    // Function that shows the Progress Dialog
    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }
}