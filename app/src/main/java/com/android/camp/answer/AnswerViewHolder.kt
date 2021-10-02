package com.android.camp.answer

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Answer
import com.android.camp.data.model.ExamAnswer

class AnswerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val layoutRoot by lazy { itemView.findViewById<View>(R.id.layout_root) }
    private val textViewType by lazy { itemView.findViewById<TextView>(R.id.text_view_type) }
    private val textViewAnswer by lazy { itemView.findViewById<TextView>(R.id.text_view_answer) }
    private val imageView by lazy { itemView.findViewById<ImageView>(R.id.image_view) }

    fun bind(answer: Answer, isFinish: Boolean, examAnswer: ExamAnswer?) {
        textViewType.text = "${answer.type}: "
        textViewAnswer.text = answer.answer


        layoutRoot.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                layoutRoot.context,
                if (answer.type == examAnswer?.type) R.color.yellow else R.color.default_answer_color
            )
        )

        textViewAnswer.setTextColor(ContextCompat.getColor(
            layoutRoot.context,
            if (answer.type == examAnswer?.type) R.color.black else R.color.white
        ))

        imageView.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                layoutRoot.context,
                if (answer.type == examAnswer?.type) R.color.black else R.color.white
            )
        )

        if(isFinish) {
            layoutRoot.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    layoutRoot.context,
                    when {
                        examAnswer?.question?.correctAnswer == answer.type -> R.color.green
                        answer.type == examAnswer?.type -> R.color.yellow
                        else -> R.color.default_answer_color
                    }
                )
            )

            textViewAnswer.setTextColor(ContextCompat.getColor(
                layoutRoot.context,
                when {
                    examAnswer?.question?.correctAnswer == answer.type -> R.color.black
                    answer.type == examAnswer?.type -> R.color.black
                    else -> R.color.white
                }
            ))

            imageView.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    layoutRoot.context,
                    when {
                        examAnswer?.question?.correctAnswer == answer.type -> R.color.black
                        answer.type == examAnswer?.type -> R.color.black
                        else -> R.color.white
                    }
                )
            )
        }
    }
}