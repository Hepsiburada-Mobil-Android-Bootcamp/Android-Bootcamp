package com.android.camp.damlaAlpan

import com.android.odevler.damlaAlpan.data.Car
import org.junit.Assert
import org.junit.Test

class CarTest {

        val car = Car(
            brand = "Citroen",
            model = "C4 Grand Picasso",

            )

        @Test
        fun carTest(){
            // success scenario
            Assert.assertTrue(car.model == "C4 Grand Picasso" && car.brand == "Citroen")

            // fail scenario
            Assert.assertFalse(car.model == "C4 Grand Picasso" && car.brand != "Citroen")
        }
    }