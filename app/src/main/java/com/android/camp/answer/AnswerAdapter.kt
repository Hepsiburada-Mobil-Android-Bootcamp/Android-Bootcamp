package com.android.camp.answer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Answer
import com.android.camp.data.model.ExamAnswer
import com.android.camp.data.model.Question

class AnswerAdapter(private var list: ArrayList<Answer>, private val isFinish:Boolean, var examAnswer: ExamAnswer?,
                    private val answered:(String) -> Unit) :
    RecyclerView.Adapter<AnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = list[position]
        holder.bind(answer, isFinish, examAnswer)

        holder.itemView.setOnClickListener {
            answer.type?.let { type -> answered(type) }
        }

    }

    override fun getItemCount() = list.size
}