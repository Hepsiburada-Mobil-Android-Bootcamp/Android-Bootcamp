package com.android.odevler.ozantakir.data

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class NbaChampTest {
    val champ = NbaChamp(teamName = "Milwaukee Bucks", championshipYear = 2021,
    finalsMvp = "Giannis Antetokounmpo", gamesPlayedInFinals = 6)

    @Test
    fun champTest() {
        // Success Case
        Assert.assertTrue(champ.teamName == "Milwaukee Bucks")

        // Fail Case
        Assert.assertFalse(champ.teamName == "Indiana Pacers")
        Assert.assertFalse(champ.teamName == "Orlando Magic")
        Assert.assertFalse(champ.teamName == "Brooklyn Nets")

    }

    @Test
    fun nbaChamp() {
        // success case
        Assert.assertTrue(champ.nbaChamp() == "The champion team is Milwaukee Bucks")

        // fail case
        Assert.assertFalse(champ.nbaChamp() == "The champion team is Indiana Pacers")
        Assert.assertFalse(champ.nbaChamp() == "The champion team is Orlando Magic")
        Assert.assertFalse(champ.nbaChamp() == "The champion team is Brooklyn Nets")

    }
}