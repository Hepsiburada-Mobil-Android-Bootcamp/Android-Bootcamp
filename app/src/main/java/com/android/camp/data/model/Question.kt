package com.android.camp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Question(
    val question: String? = "",
    val answers: ArrayList<Answer>? = arrayListOf(),
    val correctAnswer: String? = ""
)