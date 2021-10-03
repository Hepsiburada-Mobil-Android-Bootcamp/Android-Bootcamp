package com.android.odevler.sahranurer.data

import com.android.camp.data.model.Answer
import com.android.camp.data.model.Question
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test

class BookTest {
    val list = Book(
                bookTitle = "Insan Neyle Yasar",
                yearOfPublication = 2020,
                authorName = "Tolstoy",
                bookCategory = "Felsefe"

            )

    @Test
    fun bookTest(){
        // success scenario
        Assert.assertTrue(list.bookTitle == "Insan Neyle Yasar")

        // fail scenario
        Assert.assertFalse(list.yearOfPublication == 2021)
        Assert.assertFalse(list.authorName.equals("deneme"))
        Assert.assertFalse(list.bookCategory == "felsefe")
    }
}