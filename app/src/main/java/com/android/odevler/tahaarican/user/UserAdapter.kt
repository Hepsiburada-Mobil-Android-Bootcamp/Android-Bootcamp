package com.android.odevler.tahaarican.user

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.ItemVehicleBinding
import com.android.camp.databinding.UserItemBinding
import com.android.odevler.tahaarican.data.User
import com.android.odevler.tahaarican.data.mergeNameAndSurname

class UserAdapter(private val list: ArrayList<User>) :
    RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.bind(user)
    }

    override fun getItemCount() = list.size
}


