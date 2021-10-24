package com.android.camp.amazon.presentation.view

import com.android.camp.amazon.data.model.Product

interface ProductView {

    fun setProducts(list: List<Product>)
}