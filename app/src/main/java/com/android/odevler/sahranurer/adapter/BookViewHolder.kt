package com.android.odevler.sahranurer.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.camp.data.model.Exam
import com.android.camp.databinding.ItemBookBinding
import com.android.camp.databinding.ItemExamBinding
import com.android.odevler.sahranurer.data.Book

class BookViewHolder (private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book: Book) {
        binding.book = book
    }
    /*

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
     */
}