package com.android.camp

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.data.model.Exam
import com.android.camp.exam.ExamAdapter

object BindingEXt {

    @JvmStatic
    @BindingAdapter("camp:exams")
    fun setExamList(recyclerView: RecyclerView?, list: ArrayList<Exam>?) {
        if (list?.isNotEmpty() == true) {
          //  recyclerView?.adapter = recyclerView?.context?.let { ExamAdapter(it, list) }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:setProgressText")
    fun setPregress(textView: TextView?, progress:Int?){
        textView?.text = "% $progress"
    }
}