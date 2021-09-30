package com.android.odevler.talhadengiz.data.model

data class StudentExam(
    val studentNumber:Int? = 0,
    val lessonName:String? = "",
    val midtermPoint:Double? = 0.0,
    val finalPoint:Double? = 0.0
)
{
    fun calculateAverage(midterm: Double, final: Double): Double {
        return (midterm * 0.4) + (final * 0.6)
    }
}