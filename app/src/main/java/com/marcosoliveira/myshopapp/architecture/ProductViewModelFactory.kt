package com.marcosoliveira.myshopapp.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModelFactory(
    val ProductsRepository:ProductRepository
): ViewModelProvider.Factory {

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return ProductViewModel(ProductsRepository) as T
//    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(ProductsRepository) as T
    }
}