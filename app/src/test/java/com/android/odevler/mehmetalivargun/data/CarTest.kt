package com.android.odevler.mehmetalivargun.data


import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.Year

class CarTest {
    val brands= arrayListOf("Citroen","BMW","Mercedes","Opel","Renault","Ferrari","Ford")
    val car = Car("Citroen",2012,"C4",260,100)

    @Test
    fun carTest(){
        //is brand legit
        assertTrue(brands.contains(car.brand))
    }
    @Test
    fun carMaxSpeedTest(){
        assertTrue(car.maxSpeed!! <320)
    }

    @Test
    fun modelYearTest(){
        assertTrue(car.modelYear!! <=Year.now().value)
        assertTrue(car.modelYear!! >=1908)
    }
    @Test
    fun testFullGasPrice(){
        val price=7.5
        val answer=750.0
        println(fullGasPrice(price = price,car))
        assertEquals(answer,fullGasPrice(price = price,car),0.0)

    }
}