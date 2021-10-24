package com.android.camp.amazon.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val image: String? = null,
    val name: String? = null,
    val price: Int? = null,
    @SerializedName("product_id") val productId: String? = null
)