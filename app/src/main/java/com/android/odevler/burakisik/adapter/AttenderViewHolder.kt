package com.android.odevler.burakisik.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.burakisik.data.model.Attender

class AttenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvFirstName by lazy { itemView.findViewById<TextView>(R.id.tvFirstName) }
    private val tvLastName by lazy { itemView.findViewById<TextView>(R.id.tvLastName) }
    private val tvEmailAddress by lazy { itemView.findViewById<TextView>(R.id.tvEmailAddress) }

    fun bind(attender: Attender) {
        tvFirstName.text = attender.firstName
        tvLastName.text = attender.lastName
        tvEmailAddress.text = attender.email
    }
}