package com.android.odevler.talhadengiz

import com.android.odevler.talhadengiz.data.model.StudentExam
import org.junit.Assert
import org.junit.Test

class StudentExamTest {
    private val studentExam = StudentExam(
        studentNumber = 3053,
        lessonName = "Mobile Programming",
        midtermPoint = 65.0,
        finalPoint = 85.0
    )

    @Test
    fun studentExamTest(){
        //Success scenario
        Assert.assertTrue(studentExam.studentNumber == 3053)

        //Fail scenario
        Assert.assertFalse(studentExam.lessonName == "Object Oriented Programming")
        Assert.assertFalse(studentExam.midtermPoint == 65.5)
        Assert.assertFalse(studentExam.finalPoint == 80.0)
    }

    @Test
    fun calculateAverage(){
    }
}