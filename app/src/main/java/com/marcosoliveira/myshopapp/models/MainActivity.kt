package com.marcosoliveira.myshopapp.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.architecture.ProductRepository
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import com.marcosoliveira.myshopapp.architecture.ProductViewModelFactory
import com.marcosoliveira.myshopapp.architecture.Productdb
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.NonCancellable.cancel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ProductViewModel
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productRepository = ProductRepository(Productdb(this))
        val productViewModelFactory = ProductViewModelFactory(productRepository)
        viewModel= ViewModelProvider(this,productViewModelFactory).get(ProductViewModel::class.java)

        firebaseAuth = FirebaseAuth.getInstance()

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.log_out_btn, menu)
        return true
    }

    // this function log in or out the current user
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val currentUser = FirebaseAuth.getInstance().currentUser

        if(currentUser != null){

            firebaseAuth.signOut()

            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"You are logged out!", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(this,"Please log in or create an account!", Toast.LENGTH_LONG).show()
        }
        return true
    }

}