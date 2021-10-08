package com.android.odevler.tahaarican.user

import androidx.recyclerview.widget.RecyclerView
import com.android.camp.databinding.UserItemBinding
import com.android.odevler.tahaarican.data.User

class UserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User){
        binding.user=user
    }

}