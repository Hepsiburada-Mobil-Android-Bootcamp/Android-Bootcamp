package com.android.odevler.tahaarican.user

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.tahaarican.data.User
import com.android.odevler.tahaarican.data.mergeNameAndSurname

class UserAdapter(private val list: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userTextView by lazy { itemView.findViewById<TextView>(R.id.user_name) }

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            userTextView.text = mergeNameAndSurname(user)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}