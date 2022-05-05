package com.marcosoliveira.myshopapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.architecture.ProductRepository
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import com.marcosoliveira.myshopapp.architecture.ProductViewModelFactory
import com.marcosoliveira.myshopapp.architecture.Productdb

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

//    lateinit var viewModel: ProductViewModel
//    lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        val productRepository = ProductRepository(Productdb(view.context))
//        val productViewModelFactory = ProductViewModelFactory(productRepository)
//        viewModel= ViewModelProvider(this,productViewModelFactory).get(ProductViewModel::class.java)
    }

}