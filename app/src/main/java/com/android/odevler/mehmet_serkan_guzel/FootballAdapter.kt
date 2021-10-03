package com.android.odevler.mehmet_serkan_guzel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.mehmet_serkan_guzel.data.Football


class FootballAdapter(private val list: ArrayList<Football>) :
RecyclerView.Adapter<FootballViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballViewHolder {
        return FootballViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_football,parent,false)
        )
    }

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

}
