package com.android.odevler.arifumutsepetci.data.model

data class Product(
    var id: String="",
    val name: String = "",
    val price: Int? = null,
    var stock: Int? = null,
    val category: String = ""
){
    fun urunStoktaVarMi():Boolean = this.stock?:0 > 0
}

