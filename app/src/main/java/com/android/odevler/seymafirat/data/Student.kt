package com.android.odevler.seymafirat.data

data class Student(
    val name: String? = "",
    val surname: String? = "",
    val studentNumber: Int = 0,
    val classNumber: Int = 0,
)

fun createCard(student: Student) : String{
    var card:String = "${student.name} isimli öğrencinin numarası ${student.studentNumber}"
    return card
}
