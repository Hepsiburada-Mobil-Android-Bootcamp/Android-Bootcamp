package com.android.odevler.serkanozdemir.data

import org.junit.Assert.*
import org.junit.Test


class FlightTest {
    val flight = Flight("TK 1980","İstanbul","Ankara","10.00","11.00")
    @Test
    fun flightTest(){

        assertTrue(flight.flightNumber=="TK 1980")
        assertTrue(flight.from=="İstanbul")
        assertTrue(flight.to=="Ankara")
        assertTrue(flight.departureTime=="10.00")
        assertTrue(flight.landingTime=="11.00")

        assertFalse(flight.flightNumber=="TK 110")
        assertFalse(flight.from=="Ankara")
        assertFalse(flight.to=="Antalya")
        assertFalse(flight.departureTime=="11.00")
        assertFalse(flight.landingTime=="10.00")

        assertEquals("This plane going from İstanbul to Ankara", flight.flightStatus())

    }
}