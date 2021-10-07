package com.android.odevler.seymafirat.student

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.seymafirat.data.Student

class StudentViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val textViewStudentName: TextView by lazy { view.findViewById(R.id.text_view_show_name) }
    private val textViewStudentSurname: TextView by lazy { view.findViewById(R.id.text_view_show_surname) }
    //private val textViewStudentClass: TextView by lazy { view.findViewById(R.id.text_view_show_class) }
    //private val textViewStudentNumber: TextView by lazy { view.findViewById(R.id.text_view_show_number) }
    fun bind(student: Student){
        textViewStudentName.text = student.name
        textViewStudentSurname.text = student.surname
        //textViewStudentClass.text = student.classNumber.toString()
        //textViewStudentNumber.text = student.studentNumber.toString()

    }
}