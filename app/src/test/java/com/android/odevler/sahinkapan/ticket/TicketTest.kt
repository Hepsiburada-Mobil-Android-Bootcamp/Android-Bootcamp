package com.android.odevler.sahinkapan.ticket

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TicketTest{



    @Test
    fun ticketTest(){
        val ticket = Ticket(
            travelID = "1234",
            vehicleType = "plane",
            fromCity = "Adana",
            toCity = "Paris",
            price = "123.4"
        )

        assertTrue(createMessage(ticket) == "Adana'den Paris'e")

        assertFalse(ticket.travelID == "123456")


    }


}