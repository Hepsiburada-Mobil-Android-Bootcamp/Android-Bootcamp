package com.android.odevler.talhadengiz.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.talhadengiz.data.model.StudentExam

class StudentExamAdapter(private val studentExams: ArrayList<StudentExam>) :
    RecyclerView.Adapter<StudentExamAdapter.StudentExamViewHolder>() {

    class StudentExamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvStudentNumber: TextView by lazy { itemView.findViewById(R.id.tv_student_number) }
        private val tvLessonName: TextView by lazy { itemView.findViewById(R.id.tv_lesson_name) }
        private val tvMidtermPoint: TextView by lazy { itemView.findViewById(R.id.tv_midterm_point) }
        private val tvFinalPoint: TextView by lazy { itemView.findViewById(R.id.tv_final_point) }

        @SuppressLint("SetTextI18n")
        fun bind(studentExam: StudentExam) {
            tvStudentNumber.text =
                "${tvStudentNumber.context.resources.getString(R.string.student_number)} : ${studentExam.studentNumber.toString()}"
            tvLessonName.text =
                "${tvLessonName.context.resources.getString(R.string.lesson_name)} : ${studentExam.lessonName}"
            tvMidtermPoint.text =
                "${tvMidtermPoint.context.resources.getString(R.string.midterm_point)} : ${studentExam.midtermPoint.toString()}"
            tvFinalPoint.text =
                "${tvFinalPoint.context.resources.getString(R.string.final_point)} : ${studentExam.finalPoint.toString()}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentExamViewHolder {
        return StudentExamViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_student_exam, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StudentExamViewHolder, position: Int) {
        holder.bind(studentExams[position])
    }

    override fun getItemCount() = studentExams.size
}