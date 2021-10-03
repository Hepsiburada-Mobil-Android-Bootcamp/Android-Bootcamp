package com.android.odevler.akinkeskinbas.rehber

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.akinkeskinbas.data.model.Users
import org.w3c.dom.Text

class RehberAdapter(context:Context, val userList:ArrayList<Users>):RecyclerView.Adapter<RehberAdapter.PostHolder>() {

    class PostHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.name)
        val numberText = itemView.findViewById<TextView>(R.id.number)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RehberAdapter.PostHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rehber_items, parent, false)


        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: RehberAdapter.PostHolder, position: Int) {
        holder.nameText.text = userList[position].name
        holder.numberText.text = userList[position].number.toString()
    }

    override fun getItemCount(): Int {
        println(userList.size)
        return   userList.size

    }
}