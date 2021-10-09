package com.android.odevler.ahmettariksahin.model


data class Computer (
    val powerSupply:String? = "",
    val screenModel:String? = "",
    val motherBoard:String? = "",
    val ram:String? = "",
    val keyboard:String? = ""
){
    fun showPc(){
        println("$powerSupply\n" +
                "$screenModel\n" +
                "$motherBoard\n" +
                "$ram\n" +
                "$keyboard")
    }
}