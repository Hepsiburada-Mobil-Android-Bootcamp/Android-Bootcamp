package com.android.odevler.akinkeskinbas.data.model

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class UsersTest {

     val newUser = Users(
        name = "Tester1",
        number = 11111,
         mail="tester@gmail.com",
          profilUrl = "testphoto",
         yetkiliMi = false
    )
    @Test
    fun userTest(){
        // success scenario
        Assert.assertTrue(newUser.yetkiliMi==false)

        // fail scenario
        Assert.assertFalse(newUser.yetkiliMi==true)

    }
}