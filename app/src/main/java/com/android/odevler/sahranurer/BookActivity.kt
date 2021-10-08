package com.android.odevler.sahranurer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.camp.R
import com.android.camp.databinding.ActivityBookListBinding
import com.android.odevler.sahranurer.adapter.BookAdapter
import com.android.odevler.sahranurer.data.Book
import com.google.firebase.firestore.FirebaseFirestore

class BookActivity: AppCompatActivity()  {

    private var firestore:FirebaseFirestore? = null
    var binding:ActivityBookListBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this,R.layout.activity_book_list)

        binding?.floatingActionButton?.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }
        initBook()
       firestore = FirebaseFirestore.getInstance()
        bindBook()

    }

    fun initBook(){
        binding?.rv?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
    fun bindBook(){

        firestore?.collection("SahranurEr")?.get()?.addOnSuccessListener { snapshot ->
            snapshot.toObjects(Book::class.java)?.let { books ->
                binding?.rv?.adapter = BookAdapter(books as ArrayList<Book>)
            }

        }





    }





}