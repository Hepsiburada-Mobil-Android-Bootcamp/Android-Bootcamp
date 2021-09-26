package com.android.camp.answer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Answer

class AnswerAdapter(private val context: Context, private var list: ArrayList<Answer>) :
    RecyclerView.Adapter<AnswerViewHolder>() {

    private var selectedType: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = list[position]
        holder.bind(answer, selectedType)

        holder.itemView.setOnClickListener {
            selectedType = answer.type.toString()
            notifyDataSetChanged()
        }

    }

    override fun getItemCount() = list.size
}