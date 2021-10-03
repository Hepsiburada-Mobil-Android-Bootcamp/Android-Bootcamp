package com.android.odevler.edakuntalp.data.model

import org.junit.Assert
import junit.framework.TestCase
import org.junit.Test

class PlayerTest{
    val player = Player(name = "Eda",surname = "Kuntalp",bornYear = 1998,consoleType = "Pc")

    @Test
    fun playerTest(){
        // success
        Assert.assertTrue(player.name == "Eda")
        Assert.assertTrue(player.bornYear == 1998)

        // fail
        Assert.assertFalse(player.name == "Mehmet")
        Assert.assertFalse(player.bornYear == 1870)
        Assert.assertFalse(player.surname == "Kırmızı")
        Assert.assertFalse(player.consoleType == "Wii")

    }
}