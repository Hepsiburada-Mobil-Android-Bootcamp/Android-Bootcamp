package com.android.odevler.cagrikilic.data

data class Paper(
    val name:String,
    val color: String,
    val width: Int,
    val height : Int,
){
    fun calculateArea(): Int {
        return width * height
    }
}
