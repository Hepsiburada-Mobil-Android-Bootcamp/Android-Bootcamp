package com.android.odevler.muruvvetbozkurt.data

data class lectures(
    val crn : Int? = null,
    var courseCode : String?= "",
    val title : String?="",
    val instructor : String?= "",
    val capacity: Int? = null,
    val enrolled: Int? = 0,
){
    fun Quata():Boolean = this.capacity?:0 > 0
}



