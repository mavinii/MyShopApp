package com.marcosoliveira.myshopapp.models

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcosoliveira.myshopapp.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.marcosoliveira.myshopapp.adapter.CartAdapter
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import java.math.BigDecimal

class CartFragment : Fragment(R.layout.fragment_cart) {

    lateinit var viewModel: ProductViewModel
    lateinit var cartadapter: CartAdapter

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setRecyclerview()

        viewModel.getAllProducts().observe(viewLifecycleOwner, Observer {
            cartadapter.differ.submitList(it)
        })

        viewModel.price.observe(viewLifecycleOwner, Observer {
            val tvTotalPrice = view.findViewById<TextView>(R.id.tvTotalPrice)
            tvTotalPrice.text = it.toString()
        })


        // Checkout.preload(context)


        val btpay = view.findViewById<Button>(R.id.btpay)
        btpay.setOnClickListener {

            val amount = view.findViewById<TextView>(R.id.tvTotalPrice).text.toString().toBigDecimal()
            val check = BigDecimal.valueOf(0.0)

            if(amount == check){
                Toast.makeText(context,"Your cart is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amt = amount.toString().toDouble()
            val newamt = amt.times(100).toInt().toString()

            /*Toast.makeText(context,"The Amount is $newamt",Toast.LENGTH_LONG).show()*/

            navigate(newamt)
        }

    }

    // If user IS already signed in, go to CheckoutActivity
    // if NOT, go to sign in page
    private fun navigate(newamt: String) {

        val currentUser = FirebaseAuth.getInstance().currentUser

        if(currentUser != null){

            // Redirect the user to the CheckoutActivity Screen
            val intent = Intent(context, DeliveryFragment::class.java)
            startActivity(intent)
        } else {

            // if user is not signed in, go to sing in page
            val intent = Intent(context, SignInActivity::class.java)
            intent.putExtra("AMOUNT",newamt)
            startActivity(intent)
        }

    }

    private fun setRecyclerview(){

        cartadapter = CartAdapter(viewModel)

        view?.findViewById<RecyclerView>(R.id.rvCart)?.apply{
            adapter = cartadapter
            layoutManager= LinearLayoutManager(context)
        }
    }


}