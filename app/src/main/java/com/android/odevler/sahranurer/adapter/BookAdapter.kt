package com.android.odevler.sahranurer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.ItemBookBinding
import com.android.odevler.sahranurer.data.Book

 class BookAdapter(private val listBook:ArrayList<Book>) :RecyclerView.Adapter<BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(

            ItemBookBinding.inflate(LayoutInflater.from(parent.context))

        )

    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
       holder.bind(listBook[position])
    }

    override fun getItemCount() = listBook.size
}