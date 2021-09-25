package com.android.camp.question

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Question

class QuestionAdapter(private val list: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionTextView by lazy { itemView.findViewById<TextView>(R.id.text_view_question) }
        private val aRadioButton by lazy { itemView.findViewById<RadioButton>(R.id.radio_button_a) }
        private val bRadioButton by lazy { itemView.findViewById<RadioButton>(R.id.radio_button_b) }
        private val cRadioButton by lazy { itemView.findViewById<RadioButton>(R.id.radio_button_c) }
        private val dRadioButton by lazy { itemView.findViewById<RadioButton>(R.id.radio_button_d) }
        private val eRadioButton by lazy { itemView.findViewById<RadioButton>(R.id.radio_button_e) }

        @SuppressLint("SetTextI18n")
        fun bind(question: Question) {
            questionTextView.text = question.question
            aRadioButton.text = "${question.answers?.get(0)!!.type}: ${question.answers.get(0)!!.answer}"
            bRadioButton.text = "${question.answers.get(1)?.type}: ${question.answers?.get(1)?.answer}"
            cRadioButton.text = "${question.answers.get(2)?.type}: ${question.answers?.get(2)?.answer}"
            dRadioButton.text = "${question.answers.get(3)?.type}: ${question.answers?.get(3)?.answer}"
            eRadioButton.text = "${question.answers.get(4)?.type}: ${question.answers?.get(4)?.answer}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
    )

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}