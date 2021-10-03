package com.android.odevler.oguzhanyildirim.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.oguzhanyildirim.data.model.Hospital


class HospitalAdapter(private val hospitals: ArrayList<Hospital>) :
    RecyclerView.Adapter<HospitalViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewholder {
        return HospitalViewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hospital, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HospitalViewholder, position: Int) {
        holder.bind(hospitals[position])

        }


    override fun getItemCount() = hospitals.size
}