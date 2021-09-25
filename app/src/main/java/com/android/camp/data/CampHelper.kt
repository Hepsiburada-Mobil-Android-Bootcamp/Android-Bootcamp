package com.android.camp.data

import com.android.camp.data.model.Answer
import com.android.camp.data.model.Question

object CampHelper {
    val list = arrayListOf(
        Question(
            question = "Adıyamanın plakası kaçtır?",
            answers = arrayListOf(
                Answer(type = "A", answer = "01"),
                Answer(type = "B", answer = "02"),
                Answer(type = "C", answer = "03"),
                Answer(type = "D", answer = "04"),
                Answer(type = "E", answer = "05")
            ),
            correctAnswer = "B"
        ), Question(
            question = "02 nerenin plakası?",
            answers = arrayListOf(
                Answer(type = "A", answer = "Adana"),
                Answer(type = "B", answer = "Adıyaman"),
                Answer(type = "C", answer = "Çanakkale"),
                Answer(type = "D", answer = "Denizli"),
                Answer(type = "E", answer = "Edirne")
            ),
            correctAnswer = "B"
        )
    )
}