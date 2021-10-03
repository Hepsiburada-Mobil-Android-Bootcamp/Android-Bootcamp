package com.android.odevler.seymafirat.data

import junit.framework.TestCase
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StudentTest {

    @Test
    fun studentTest(){
        val student = Student(
            name = "Eslem",
            surname =  "Fırat",
            classNumber = 5,
            studentNumber = 2380
        )
        assertTrue(createCard(student) == "Eslem isimli öğrencinin numarası 2380")
        assertFalse(student.surname == "133")
    }

}