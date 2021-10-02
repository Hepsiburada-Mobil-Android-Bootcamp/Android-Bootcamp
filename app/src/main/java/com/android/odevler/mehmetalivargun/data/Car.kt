package com.android.odevler.mehmetalivargun.data

import java.io.Serializable

data class Car(
    val brand : String?="",
    val modelYear: Int?=null,
    val model : String?="",
    val maxSpeed: Int?=null,
    val gasCapacity:Int?=null
    ): Serializable

fun fullGasPrice(price: Double, car:Car):Double{
    return  price* car.gasCapacity!!


}


