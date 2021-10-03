package com.android.odevler.serhadmert.data

data class Anime(
    val name: String = "",
    val type: String = "",
    val malPoint: Double? = null,
    val releaseDate: String?= null,
    var worthToWatch: Boolean?=null
){
    fun worthToWatch(){
        worthToWatch = this.malPoint!!>8.00
    }
}
