package com.android.camp.data.model

data class Question(
    val question: String,
    val answers: ArrayList<Answer>,
    val correctAnswer: String
) {

    fun countAnswer(): Int {
        return answers.count()
    }
}