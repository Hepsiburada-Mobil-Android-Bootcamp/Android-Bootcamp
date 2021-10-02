package com.android.odevler.abdulwahid.data

import junit.framework.TestCase
import org.junit.Test

class SongTest : TestCase() {

    private val testSong = Song("Gravity", "Boris Brejcha", 9.25, 2019)

    @Test
    fun testSong() {
        // Success Scenario
        assertTrue(testSong.name == "Gravity")

        // Fail Scenario
        assertFalse(testSong.name == "The Darkest Night")
        assertFalse(testSong.name == "Kitty Journey")
        assertFalse(testSong.name == "Purple Noise")
    }

    @Test
    fun testPlaySong() {
        // Success Scenario
        assertTrue(testSong.isPlaying.not())
        testSong.playSong()
        assertTrue(testSong.isPlaying)
        testSong.stopSong()
        assertTrue(testSong.isPlaying.not())

        // Fail Scenario
        assertFalse(testSong.isPlaying)
        testSong.playSong()
        assertFalse(testSong.isPlaying.not())
        testSong.stopSong()
        assertFalse(testSong.isPlaying)
    }
}