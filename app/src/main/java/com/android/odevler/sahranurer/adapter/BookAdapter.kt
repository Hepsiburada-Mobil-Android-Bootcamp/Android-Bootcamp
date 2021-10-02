package com.android.odevler.sahranurer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.sahranurer.data.Book

 class BookAdapter(private val listBook:ArrayList<Book>) :RecyclerView.Adapter<BookAdapter.bookViewHolder>() {

    inner class bookViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private val textViewbookTitle by lazy {itemView.findViewById<TextView>(R.id.textViewbookTitle)}
        private val textViewyearOfPublication by lazy {itemView.findViewById<TextView>(R.id.textViewyearOfPublication)}
        private val textViewauthorName by lazy {itemView.findViewById<TextView>(R.id.textViewauthorName)}
        private val textViewbookCategory by lazy {itemView.findViewById<TextView>(R.id.textViewbookCategory)}




        fun bind(book: Book){
            textViewbookTitle.text = book.bookTitle
            textViewyearOfPublication.text = book.yearOfPublication.toString()
            textViewauthorName.text = book.authorName
            textViewbookCategory.text = book.bookCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookViewHolder {
        return bookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false))
    }

    override fun onBindViewHolder(holder: bookViewHolder, position: Int) {
       holder.bind(listBook[position])
    }

    override fun getItemCount() = listBook.size
}