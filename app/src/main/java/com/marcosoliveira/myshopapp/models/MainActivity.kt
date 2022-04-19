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
        menuInflater.inflate(R.menu.app_menu,menu)
        return true
    }

    // If an user have already logged in
    // this function log out a current user
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val currentUser = FirebaseAuth.getInstance().currentUser

        if(currentUser != null){
            firebaseAuth.signOut()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"You are logged out!",Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(this,"Please log in or create an account!",Toast.LENGTH_LONG).show()
        }
        return true
    }

    /* override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
         val map = HashMap<String, String>()

         map["order_id"] = p1!!.orderId
         map["pay_id"] = p1.paymentId
         map["signature"] = p1.signature


         viewModel.updatetransaction(map)

          viewModel.update.observe(this, Observer {
              when(it){
                  is Resource.Success->{
                      val message=it.message!!
                      Toast.makeText(this,"Error occured $message",Toast.LENGTH_LONG).show()
                  }

                  is Resource.Error->{
                      if(it.data=="success"){
                          Toast.makeText(this,"Payment Successful",Toast.LENGTH_LONG).show()
                      }
                  }
              }
          })

     }*/

}