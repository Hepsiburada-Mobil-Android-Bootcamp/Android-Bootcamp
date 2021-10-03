package com.android.odevler.cagrikilic.data

data class Paper(
    val name:String ?="",
    val color: String?="",
    val width: Int ?=null,
    val height : Int ?=null,
){
    fun calculateArea(): Int {
        return width!! * height!!
    }
}
