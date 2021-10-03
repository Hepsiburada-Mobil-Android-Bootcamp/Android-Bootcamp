package com.android.odevler.sahranurer.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Question
import com.android.camp.question.QuestionAdapter
import com.android.odevler.sahranurer.data.Book
import com.google.firebase.firestore.FirebaseFirestore

class BookActivity: AppCompatActivity()  {

    private var firestore:FirebaseFirestore? = null

    private val recyclerView by lazy{findViewById<RecyclerView>(R.id.rv)}
    private val floatingActionButton by lazy{findViewById<View>(R.id.floatingActionButton)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        floatingActionButton.setOnClickListener {
            val intent = Intent(this,AddBookActivity::class.java)
            startActivity(intent)
        }
        initBook()
       firestore = FirebaseFirestore.getInstance()
        bindBook()

    }

    fun initBook(){
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
    fun bindBook(){

        firestore?.collection("SahranurEr")?.get()?.addOnSuccessListener { snapshot ->
            snapshot.toObjects(Book::class.java)?.let { books ->
                recyclerView.adapter = BookAdapter(books as ArrayList<Book>)
            }

        }





    }





}