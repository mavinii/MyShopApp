package com.marcosoliveira.myshopapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.util.Resource
import com.marcosoliveira.myshopapp.adapter.ProductAdapter
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import com.marcosoliveira.myshopapp.ui.activities.MainActivity

class ProductsFragment : Fragment(R.layout.fragment_products) {

    lateinit var viewModel: ProductViewModel
    lateinit var Productadapter: ProductAdapter

    val TAG="ProductsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel

        setRecyclerview()

        val etSearch = view.findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener { editable->
            val category = editable.toString()
            if(category == "electronics" || category== "jewelery" || category== "men's clothing" || category== "women's clothing"){
                viewModel.searchProducts(category)
            }
        }


        viewModel.SearchProducts.observe(viewLifecycleOwner, Observer{

            val paginationProgressBar = view.findViewById<ProgressBar>(R.id.progressBar)

            when(it){
                is Resource.Success->{
                    paginationProgressBar.visibility= View.INVISIBLE
                    it.data?.let{NewProducts->
                        Productadapter.differ.submitList(NewProducts)
                    }
                }

                is Resource.Error->{
                    paginationProgressBar.visibility= View.INVISIBLE
                    val message=it.message
                    Log.e(TAG,"an error occured $message")
                }

                is Resource.Loading->{
                    paginationProgressBar.visibility= View.VISIBLE
                }
            }
        })

        viewModel.Getproducts.observe(viewLifecycleOwner, Observer{

            val paginationProgressBar = view.findViewById<ProgressBar>(R.id.paginationProgressBar)

            when(it){
                is Resource.Success->{
                    paginationProgressBar.visibility = View.INVISIBLE

                    it.data?.let{NewProducts->
                        Productadapter.differ.submitList(NewProducts)
                    }
                }

                is Resource.Error->{
                    paginationProgressBar.visibility = View.INVISIBLE

                    val message=it.message
                    Log.e(TAG,"an error occured $message")
                }

                is Resource.Loading->{
                    paginationProgressBar.visibility = View.VISIBLE

                }
            }
        })

        val fabCart = view.findViewById<FloatingActionButton>(R.id.fabCart)
        fabCart.setOnClickListener {
            findNavController(view).navigate(R.id.action_productFragment_to_cartFragment)
        }
    }

    private fun setRecyclerview(){
        Productadapter= ProductAdapter(viewModel)

        view?.findViewById<RecyclerView>(R.id.rvProduct)?.apply{
//        rvProduct.apply{
            adapter=Productadapter
            layoutManager= LinearLayoutManager(context)
        }
    }


}