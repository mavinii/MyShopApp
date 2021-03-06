package com.marcosoliveira.myshopapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcosoliveira.myshopapp.R
import com.marcosoliveira.myshopapp.architecture.ProductViewModel
import com.marcosoliveira.myshopapp.response.Products
import kotlinx.coroutines.*

class ProductAdapter(val viewModel: ProductViewModel): RecyclerView.Adapter<ProductAdapter.Productviewholder>() {

    inner class Productviewholder(item: View): RecyclerView.ViewHolder(item)

    private val differcallback=object: DiffUtil.ItemCallback<Products>(){
        override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem==newItem
        }
    }

    val differ=AsyncListDiffer(this,differcallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Productviewholder {
        return Productviewholder(LayoutInflater.from(parent.context).inflate(R.layout.product_view,parent,false))
    }

    override fun onBindViewHolder(holder: Productviewholder, position: Int) {
        val product=differ.currentList[position]
        holder.itemView.apply {

            val ivArticleImage = findViewById<ImageView>(R.id.ivArticleImage)
            val pricetv = findViewById<TextView>(R.id.pricetv)
            val titletv = findViewById<TextView>(R.id.titletv)
            val categorytv = findViewById<TextView>(R.id.categorytv)
            val addtocartbt = findViewById<Button>(R.id.addtocartbt)


            Glide.with(this).load(product.image).into(ivArticleImage)
            pricetv.text=product.price.toString()
            titletv.text=product.title
            categorytv.text=product.category

            addtocartbt.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val ans = viewModel.checkid(product.id!!).await()

                    withContext(Dispatchers.Main){
                        if(ans == 0){
                            product.amt="1"
                            Toast.makeText(context,"Item ${titletv.text} is added to cart successfully ${product.amt}",
                                Toast.LENGTH_LONG).show()
                            viewModel.upsert(product)
                            viewModel.increase(product.price!!.toBigDecimal())
                        }
                        else{
                            Toast.makeText(context,"Item already added to the cart", Toast.LENGTH_LONG).show()
                        }
                    }

                }

            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}