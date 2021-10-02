package com.android.odevler.ebraryucel.data

import org.junit.Assert.*

import org.junit.Test

class AirplaneTest {

    @Test
    fun controlInfoString() {
        val airplane=Airplane("Airbus","A320","Lufthansa",180)

        assertEquals("Airplane manufacturer : Airbus model : A320 owner : Lufthansa  capacity : 180",airplane.info())

        assertTrue("Airplane manufacturer : Airbus model : A320 owner : Lufthansa  capacity : 180"==airplane.info())

        assertFalse("Airplane manufacturer : Airbus model : A320 owner : Lufthansa  capacity : 250"==airplane.info())


    }
}