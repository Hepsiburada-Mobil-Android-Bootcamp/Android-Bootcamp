package com.android.camp.exam

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.camp.data.model.Exam
import com.android.camp.databinding.ItemExamBinding
import com.android.camp.question.QuestionsActivity
import com.google.firebase.firestore.Query
import com.yasincetin.firebasesdk.firestore.FirestoreAdapter

class ExamAdapter(private val context: Context, private val query: Query?) :
    FirestoreAdapter<ExamViewHolder, Exam>(query) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        return ExamViewHolder(
            ItemExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        getItem(position)?.let { exam ->
            holder.bind(exam)
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context, QuestionsActivity::class.java).apply {
                    putExtra("EXAM_ID", exam.id)
                })
            }
        }
    }

    override fun getModelClass() = Exam::class.java
}