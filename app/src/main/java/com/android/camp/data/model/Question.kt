package com.android.camp.data.model


import java.io.Serializable

data class Question(
    val question: String? = "",
    val answers: ArrayList<Answer>? = arrayListOf(),
    val correctAnswer: String? = ""
)