package com.marcosoliveira.myshopapp.architecture

import com.marcosoliveira.myshopapp.response.Products
import com.marcosoliveira.myshopapp.response.RetrofitInstance

class ProductRepository(
    private val db:Productdb
) {

    suspend fun getproducts() = RetrofitInstance.api.getProducts()

    suspend fun searchproducts(name: String) = RetrofitInstance.api.searchproducts(name)

    suspend fun upsert(products: Products) = db.getArticleDao().upsert(products)

    suspend fun delete(products: Products) = db.getArticleDao().delete(products)

    fun getAllproducts() = db.getArticleDao().getallProducts()

    suspend fun checkid(Id: Int) = db.getArticleDao().checkid(Id)

    suspend fun allproducts() = db.getArticleDao().allProducts()


}