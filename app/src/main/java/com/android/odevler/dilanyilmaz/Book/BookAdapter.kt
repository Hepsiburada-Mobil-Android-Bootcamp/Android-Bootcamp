package com.android.odevler.dilanyilmaz.Book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.dilanyilmaz.Model.Book

class BookAdapter(private val BookList : ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.MyViewHolder {
      val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_book_list_for_recyclerview,
      parent,false)
      return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookAdapter.MyViewHolder, position: Int) {
        val book : Book = BookList[position]
        holder.bookname.text=book.name
    }

    override fun getItemCount(): Int {
       return BookList.size
    }
    public class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val bookname : TextView=itemView.findViewById(R.id.itemkitapadi)
    }
}