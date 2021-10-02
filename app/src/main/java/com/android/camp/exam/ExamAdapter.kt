package com.android.camp.exam

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Exam
import com.android.camp.databinding.ItemExamBinding
import com.android.camp.question.QuestionsActivity

class ExamAdapter(private val context: Context, private val list: ArrayList<Exam>) :
    RecyclerView.Adapter<ExamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        return ExamViewHolder(
            ItemExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}