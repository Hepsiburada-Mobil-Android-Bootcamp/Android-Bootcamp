package com.android.odevler.nahitfidanci

import com.android.odevler.nahitfidanci.data.Galaxy
import org.junit.Assert
import org.junit.Test

class GalaxyTest {

    private val galaxy = Galaxy(
        "Andromeda",
        2537000,
        1000000000000,
        1612
    )

    @Test
    fun distanceTest() {
        //Success
        Assert.assertTrue(galaxy.lightYearDistance == 2537000)
        Assert.assertTrue(galaxy.name == "Andromeda")
        Assert.assertTrue(galaxy.exploredYear == 1612)

        // Failure
        Assert.assertFalse(galaxy.lightYearDistance == 1564658)
        Assert.assertFalse(galaxy.name == "Milkway")
        Assert.assertFalse(galaxy.exploredYear == 1845)
    }

    @Test
    fun exploreStarTest() {
        //Success
        Assert.assertTrue(galaxy.exploreNewStar() == 1000000000001)

        //Failure
        Assert.assertFalse(galaxy.exploreNewStar() == 1213215646454)

    }


}