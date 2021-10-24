package com.android.camp.amazon.data.model

import com.google.gson.annotations.SerializedName

data class ProductDetailModel(
    @SerializedName("description") val aciklama: String? = null,
    @SerializedName("image") val resim: String? = null,
    @SerializedName("name") val isim: String? = null,
    @SerializedName("price") val fiyat: Int? = null,
    @SerializedName("product_id") val urunId: String? = null
)