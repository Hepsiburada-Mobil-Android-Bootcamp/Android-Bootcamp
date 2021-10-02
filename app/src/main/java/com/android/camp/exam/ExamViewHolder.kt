package com.android.camp.exam

import androidx.recyclerview.widget.RecyclerView
import com.android.camp.data.model.Exam
import com.android.camp.databinding.ItemExamBinding

class ExamViewHolder(private val binding: ItemExamBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(exam: Exam) {
        binding.exam = exam
    }
}