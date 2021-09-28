package com.android.odevler.talhadengiz

data class StudentExam(
    val studentNumber:Int,
    val lessonName:String,
    val midtermPoint:Double,
    val finalPoint:Double
){
    fun calculateAverage(midterm: Double, final: Double): Double {
        return (midterm * 0.4) + (final * 0.6)
    }
}