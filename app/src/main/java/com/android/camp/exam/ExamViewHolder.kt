package com.android.camp.exam

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Exam

class ExamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textViewName: TextView by lazy { view.findViewById(R.id.text_view_name) }
    private val layoutRoot: View by lazy { view.findViewById(R.id.layout_root) }

    fun bind(exam: Exam) {
        textViewName.text = exam.name
    }
}