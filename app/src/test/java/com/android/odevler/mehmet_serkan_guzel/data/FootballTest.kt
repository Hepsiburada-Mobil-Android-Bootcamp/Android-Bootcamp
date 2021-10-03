package com.android.odevler.mehmet_serkan_guzel.data


import org.junit.Assert
import org.junit.Test

class FootballTest {
   private val team = Football(
        teamName = "Fenerbahce",
        year = 1907,
        bestPlayer = "Pelkas",
        teamColor = "Yellow-Dark blue"
    )
    @Test
    fun footballTest() {
        // success scenario
        Assert.assertTrue(team.teamName == "Fenerbahce")
        Assert.assertTrue(team.bestPlayer == "Pelkas")
        // fail scenario
        Assert.assertFalse(team.year == 1910)
        Assert.assertFalse(team.teamColor == "Blue-Yellow")
    }
    @Test
    fun yearToString_isCorrect() {
        // success scenario
        Assert.assertTrue(team.yearToString() == "1907")
        // fail scenario
        Assert.assertFalse(team.yearToString() == "1905")
    }


}