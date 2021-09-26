package com.android.camp.data.model

data class Question(
    val question: String? = "",
    val answers: ArrayList<Answer>? = arrayListOf(),
    val correctAnswer: String? = "",
    val date: Long? = null
)