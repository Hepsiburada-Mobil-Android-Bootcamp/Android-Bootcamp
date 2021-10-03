package com.android.odevler.seymafirat.student

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.seymafirat.data.Student

class StudentAdapter(private val list: ArrayList<Student>): RecyclerView.Adapter<StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_student,parent,false)
        )
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size



}