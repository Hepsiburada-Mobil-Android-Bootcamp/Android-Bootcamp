package com.android.odevler.sahranurer.adapter


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.camp.R
import com.android.odevler.sahranurer.data.Book
import com.google.firebase.firestore.FirebaseFirestore

class AddBookActivity : AppCompatActivity() {

    private val buttonAdd by lazy { findViewById<Button>(R.id.buttonKaydet) }
    private val editTextbookTitle by lazy { findViewById<EditText>(R.id.editTextbookTitle) }
    private val editTextyearOfPublication by lazy { findViewById<EditText>(R.id.editTextyearOfPublication) }
    private val editTextauthorName by lazy { findViewById<EditText>(R.id.editTextauthorName) }
    private val editTextbookCategory by lazy { findViewById<EditText>(R.id.editTextbookCategory) }



    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_book)


        buttonAdd.setOnClickListener {

                save()

        }
        firestore = FirebaseFirestore.getInstance()
    }

    private fun save() {
        Log.d("AddBookActivity", "valid form.... şu an kaydedilebilirrrrr")
        val book = Book(
                bookTitle = editTextbookTitle.text.toString(),
                yearOfPublication = editTextyearOfPublication.text.toString().toInt(),
                authorName = editTextauthorName.text.toString(),
                bookCategory = editTextbookCategory.text.toString())
        BookHelper.list.add(book)


        firestore?.collection("SahranurEr")?.add(book)?.addOnSuccessListener {
                   Log.e("AddNewBook", "Eklendi..")
                   finish()
            }?.addOnFailureListener{
                Toast.makeText(this, "Book Eklenemedi", Toast.LENGTH_LONG).show()

                }

            }

        }




