package com.android.odevler.tahaarican

import org.junit.Assert
import org.junit.Test

class UserTest {

    val user = User(
        id = 1,
        username = "tahaarican",
        password = "123",
        name = "taha",
        surName = "arıcan"
    )

    @Test
    fun userTest(){
        // success scenario
        Assert.assertTrue(user.username == "tahaarican")

        // fail scenario
        Assert.assertFalse(user.id.equals("1"))
        Assert.assertFalse(user.password.equals(123))
        Assert.assertFalse(user.name == "")

    }
}