package com.marcosoliveira.myshopapp.models


interface PaymentResultListener {
    fun onPaymentSuccess(var1: String?)
    fun onPaymentError(var1: Int, var2: String?)
}
