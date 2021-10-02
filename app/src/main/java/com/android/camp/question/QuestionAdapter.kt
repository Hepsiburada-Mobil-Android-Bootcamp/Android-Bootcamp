package com.android.camp.question

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.answer.AnswerAdapter
import com.android.camp.data.model.Answer
import com.android.camp.data.model.ExamAnswer
import com.android.camp.data.model.Question

class QuestionAdapter(private val context: Context, val list: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private var answerAdapter: AnswerAdapter? = null
    val myAnswerList = arrayListOf<ExamAnswer>()
    var isFinish = false

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionTextView by lazy { itemView.findViewById<TextView>(R.id.text_view_question) }
        private val recyclerViewAnswer by lazy { itemView.findViewById<RecyclerView>(R.id.recycler_view_answer) }

        @SuppressLint("SetTextI18n")
        fun bind(question: Question) {
            questionTextView.text = question.question
            recyclerViewAnswer.layoutManager = GridLayoutManager(context, 2)
            question.answers?.let {
                answerAdapter = AnswerAdapter(
                    it,
                    isFinish,
                    myAnswerList.firstOrNull { it.question.id == question.id }) { answered ->
                    val examAnswer = ExamAnswer(question = question, type = answered)
                    myAnswerList.add(examAnswer)
                    answerAdapter?.examAnswer = examAnswer
                    notifyDataSetChanged()
                }
                recyclerViewAnswer.adapter = answerAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
    )

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun sonuclandir() {
        isFinish = true
        notifyDataSetChanged()
    }
}