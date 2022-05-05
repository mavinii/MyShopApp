package com.marcosoliveira.myshopapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.architecture.ProductRepository
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import com.marcosoliveira.myshopapp.architecture.ProductViewModelFactory
import com.marcosoliveira.myshopapp.architecture.Productdb
import com.google.firebase.auth.FirebaseAuth

// 22931 - Marcos Oliveira 6:00 / 6:12 / 6:23
// 6:21 ele mostra o dashboard ele com navContoller
// tava 6:24:14
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ProductViewModel
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        val productRepository = ProductRepository(Productdb(this))
        val productViewModelFactory = ProductViewModelFactory(productRepository)
        viewModel= ViewModelProvider(this,productViewModelFactory).get(ProductViewModel::class.java)




    }

    // It adds the settings menu icon on the top
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu_btn, menu)
        return true
    }

    // it takes the user to the user profile screen
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent= Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
        return true
    }

}