package com.android.odevler.serhadmert.data

import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class AnimeTest {

    val anime=Anime("Fair Tail","Action, Adventure, Comedy, Fantasy",7.61)

    @Test
    fun animeTest(){

        assertTrue(anime.name=="Fairy Tail")

        assertFalse(anime.name=="Black Clover")

    }
}