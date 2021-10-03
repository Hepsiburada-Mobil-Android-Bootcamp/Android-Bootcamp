package com.android.odevler.tahaarican.data

import org.junit.Assert
import org.junit.Test

class UserTest {

    val user = User(
        id = "1",
        username = "tahaarican",
        password = "123",
        name = "taha",
        surname = "arıcan"
    )

    @Test
    fun userTest(){
        // success scenario
        Assert.assertTrue(user.username == "tahaarican")

        // fail scenario
        Assert.assertFalse(user.id?.equals(1) ?: false )
        Assert.assertFalse(user.password?.equals(123) ?: false )
        Assert.assertFalse(user.name == "")

        Assert.assertTrue(mergeNameAndSurname(user) == "taha arıcan")

        Assert.assertFalse(mergeNameAndSurname(user) == "")
        Assert.assertFalse(mergeNameAndSurname(user) == "tahaarıcan")


    }
}