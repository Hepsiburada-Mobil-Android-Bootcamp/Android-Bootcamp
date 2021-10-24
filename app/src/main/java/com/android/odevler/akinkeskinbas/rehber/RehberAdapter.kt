package com.android.odevler.akinkeskinbas.rehber

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.base.viewholder.BaseViewHolder
import com.android.camp.R
import com.android.odevler.akinkeskinbas.data.model.Users
import com.google.firebase.firestore.Query
import com.yasincetin.firebasesdk.firestore.FirestoreAdapter

class RehberAdapter(query: Query?) : FirestoreAdapter<RehberAdapter.PostHolder, Users>(query) {

    class PostHolder(itemView: View) : BaseViewHolder<Users>(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.name)
        val numberText = itemView.findViewById<TextView>(R.id.number)

        override fun toBind(model: Users) {
            super.toBind(model)

            nameText.text = model.name
            numberText.text = model.number.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rehber_items, parent, false)

        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getModelClass() = Users::class.java
}