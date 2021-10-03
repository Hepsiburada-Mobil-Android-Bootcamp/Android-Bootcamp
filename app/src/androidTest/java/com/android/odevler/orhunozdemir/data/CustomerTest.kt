package com.android.odevler.orhunozdemir.data

import org.junit.Assert
import org.junit.Test

class CustomerTest{


    val customer:Customer= Customer("Orhun","Özdemir",23)


    @Test
    fun customerTestTrue(){

        Assert.assertTrue(customer.name=="Orhun")
        Assert.assertTrue(customer.surname=="Özdemir")
        Assert.assertEquals(23,customer.age)
        Assert.assertEquals("Orhun-Özdemir",customer.fullname())





    }
    @Test
    fun customerTestFalse(){

        Assert.assertFalse(customer.name=="Ahmet")
        Assert.assertFalse(customer.surname=="Öztürk")
        Assert.assertFalse(customer.age==24)
        Assert.assertFalse(customer.fullname()=="Orhun")


    }





}