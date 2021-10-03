package com.android.odevler.sahinkapan.ticket

import java.io.Serializable

data class Ticket(
    var travelID:String?="",
    val vehicleType:String?="",
    val fromCity:String?="",
    val toCity:String?="",
    val price:String?=""
)
fun createMessage(ticket:Ticket) : String{
    var message:String="${ticket.fromCity}'den ${ticket.toCity}'e"
    return message
}
