package com.android.odevler.serkanozdemir.data

data class Flight(
    val flightNumber: String="",
    val from: String="",
    val to:String="",
    val departureTime:String="",
    val landingTime:String=""
) {
    fun flightStatus(): String {
        return "This plane going from $from to $to"
    }

}