package com.android.camp.data.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class QuestionTest {

    val question = Question(
        question = "Adıyamanmessagetypeın plakası kaçtır?",
        answers = arrayListOf(
            Answer(type = "A", answer = "01"),
            Answer(type = "B", answer = "02"),
            Answer(type = "C", answer = "03"),
            Answer(type = "D", answer = "04"),
            Answer(type = "E", answer = "05")
        ),
        correctAnswer = "B"
    )

    @Test
    fun questionTest(){
        // success scenario
        assertTrue(question.correctAnswer == "B")

        // fail scenario
        assertFalse(question.correctAnswer == "A")
        assertFalse(question.correctAnswer == "C")
        assertFalse(question.correctAnswer == "D")
        assertFalse(question.correctAnswer == "E")
    }

    @Test
    fun countAnswer() {
        // success scenario
        assertTrue(question.countAnswer() == 5)

        // fail scenario
        assertFalse(question.countAnswer() == 2)
    }
}