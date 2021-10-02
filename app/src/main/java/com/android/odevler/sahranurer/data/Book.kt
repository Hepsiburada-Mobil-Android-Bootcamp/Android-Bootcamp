package com.android.odevler.sahranurer.data

import java.io.Serializable

data class Book(
    val bookTitle:String?  = "",
    val yearOfPublication:Int? = null ,
    val authorName:String? = "",
    val bookCategory: String? = ""):Serializable {
}