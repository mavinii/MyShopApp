package com.marcosoliveira.myshopapp.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.architecture.ProductRepository
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import com.marcosoliveira.myshopapp.architecture.ProductViewModelFactory
import com.marcosoliveira.myshopapp.architecture.Productdb
import com.google.firebase.auth.FirebaseAuth
import com.marcosoliveira.myshopapp.util.Constants
import kotlinx.android.synthetic.main.activity_user_profile.*

// 22931 - Marcos Oliveira 6:00 / 6:12 / 6:23
// 6:21 ele mostra o dashboard ele com navContoller
// tava 4:50
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(Constants.MYSHOPAPP_PREFERENCES, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(Constants.LOGGED_IN_USERNAME, "")!!

//        user_profile_name.setText(username)

        val productRepository = ProductRepository(Productdb(this))
        val productViewModelFactory = ProductViewModelFactory(productRepository)
        viewModel = ViewModelProvider(this, productViewModelFactory).get(ProductViewModel::class.java)

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