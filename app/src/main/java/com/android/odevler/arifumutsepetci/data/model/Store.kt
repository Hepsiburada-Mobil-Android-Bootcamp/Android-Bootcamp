package com.android.odevler.arifumutsepetci.data.model

data class Store(
     var id: String = "",
     val name : String="",
     val products : ArrayList<Product>? = arrayListOf(),
     val date : Long? = null,
     val address: String= "",
)