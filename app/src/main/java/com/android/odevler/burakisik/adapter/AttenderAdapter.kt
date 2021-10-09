package com.android.odevler.burakisik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.burakisik.data.model.Attender

class AttenderAdapter(private var attenders:ArrayList<Attender>) : RecyclerView.Adapter<AttenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttenderViewHolder {
        return AttenderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.burakisik_item_attender, parent,false))
    }

    override fun onBindViewHolder(holder: AttenderViewHolder, position: Int) {
        holder.bind(attenders[position])

    }

    override fun getItemCount() = attenders.size
}