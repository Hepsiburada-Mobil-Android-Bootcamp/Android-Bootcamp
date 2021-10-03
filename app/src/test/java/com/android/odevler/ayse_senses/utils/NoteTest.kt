package com.android.odevler.ayse_senses.utils

import com.android.odevler.ayse_senses.data.model.Note
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test


class NoteTest {

    val note1 = Note(
        title = "title1",
        description = "des1",
        priority = 8,
        date = 12
    )

    @Before //This is executed before the @Test executes
    fun setUp() {
        println("Ready for testing")
    }

    @Test
    fun noteTest() {
        val note2 = Note("title", priority = 8, description = "des", date = 54)

        val actual = note2.priority
        val expected = note1.priority

        //success scenario
        assertEquals(actual, expected)
        assertNotNull(note2.title)

        Assert.assertTrue(note1.date!! < note2.date!!)
        Assert.assertTrue(note1.title != note2.title)
        Assert.assertTrue(note1.description != note2.description)

        // fail scenario
        Assert.assertFalse(note1.date!! > note2.date!!)
        Assert.assertFalse(note1.title == note2.title)
        Assert.assertFalse(note1.description == note2.description)

    }

    @After //This is executed after the @Test executes
    fun tearDown() {
        println("Done with testing")
    }
}