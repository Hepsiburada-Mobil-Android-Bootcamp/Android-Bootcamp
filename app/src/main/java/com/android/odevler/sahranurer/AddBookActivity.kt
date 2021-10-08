package com.android.odevler.sahranurer


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityAddNewBookBinding
import com.android.odevler.sahranurer.adapter.BookHelper
import com.android.odevler.sahranurer.data.Book
import com.google.firebase.firestore.FirebaseFirestore

class AddBookActivity : AppCompatActivity() {



    private var binding:ActivityAddNewBookBinding? = null

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_book)

        binding?.buttonKaydet?.setOnClickListener {
            save()
        }


        firestore = FirebaseFirestore.getInstance()
    }

    private fun save() {
        Log.d("AddBookActivity", "valid form.... şu an kaydedilebilirrrrr")
        val book = Book(
                bookTitle = binding?.editTextbookTitle?.text.toString(),
                yearOfPublication = binding?.editTextyearOfPublication?.text.toString().toInt(),
                authorName = binding?.editTextauthorName?.text.toString(),
                bookCategory = binding?.editTextbookCategory?.text.toString())
        BookHelper.list.add(book)


        firestore?.collection("SahranurEr")?.add(book)?.addOnSuccessListener {
                   Log.e("AddNewBook", "Eklendi..")
                   finish()
            }?.addOnFailureListener{
                Toast.makeText(this, "Book Eklenemedi", Toast.LENGTH_LONG).show()

                }

            }

        }




