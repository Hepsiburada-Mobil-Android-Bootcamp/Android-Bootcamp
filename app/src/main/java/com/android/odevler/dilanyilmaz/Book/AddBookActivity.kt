package com.android.odevler.dilanyilmaz.Book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.camp.data.model.Exam
import com.android.odevler.dilanyilmaz.Model.Book
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddBookActivity : AppCompatActivity() {

    private val buttonSave by lazy { findViewById<View>(R.id.button_save) }
    private val inputbookName by lazy { findViewById<EditText>(R.id.input_book_name) }
    private val inputbookWriter by lazy { findViewById<EditText>(R.id.input_book_writer) }
    private val inputbookPage by lazy { findViewById<EditText>(R.id.input_book_page) }
    private val inputbookYear by lazy { findViewById<EditText>(R.id.input_book_year) }


    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        buttonSave.setOnClickListener {
                save()
        }

        firestore = FirebaseFirestore.getInstance()
    }

    private fun save() {
        val book =
            Book(
                name = inputbookName.text.toString(),
                writer = inputbookWriter.text.toString(),
                page = inputbookPage.text.toString(),
                year = inputbookYear.text.toString()
            )

        firestore?.collection("dilanyilmaz")?.add(book)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Book Eklenemedi...", Toast.LENGTH_LONG).show()
            }
        }
    }
}