package com.android.odevler.cagrikilic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.camp.data.CampHelper
import com.android.camp.data.model.Answer
import com.android.camp.data.model.Question
import com.android.odevler.cagrikilic.data.Paper
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddPaperActivity : AppCompatActivity() {
    private val editTextPaperName by lazy { findViewById<EditText>(R.id.edit_text_name) }
    private val editTextPaperColor by lazy { findViewById<EditText>(R.id.edit_text_color) }
    private val editTextPaperWidth by lazy { findViewById<EditText>(R.id.edit_text_width) }
    private val editTextPaperHeight by lazy { findViewById<EditText>(R.id.edit_text_height) }
    private val buttonSave by lazy { findViewById<View>(R.id.button_savePaper) }
    private var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_paper)

        buttonSave.setOnClickListener {
            if(isAllFieldsFilled()){
                saveToFireBase()
            }
            else{
                Toast.makeText(applicationContext,"Hey, check that paper entry again!",Toast.LENGTH_SHORT).show()
            }
        }
        firestore = FirebaseFirestore.getInstance()
    }

    private fun isAllFieldsFilled():Boolean{
        return editTextPaperName.text.toString() != "" && editTextPaperColor.text.toString() != "" &&
                editTextPaperWidth.text.toString() != "" && editTextPaperHeight.text.toString() != ""
    }
    private fun saveToFireBase(){


        val paper = Paper(
            name = editTextPaperName.text.toString(),
            color = editTextPaperColor.text.toString(),
            width = editTextPaperWidth.text.toString().toInt(),
            height = editTextPaperHeight.text.toString().toInt(),
        )

        firestore?.collection("cagrikilic")?.add(paper)
            ?.addOnSuccessListener {
                Toast.makeText(applicationContext,"That's ok, move on to next one",Toast.LENGTH_SHORT).show()
            }
            ?.addOnFailureListener {
                Toast.makeText(this, "We have a connection problem.. In our relationship", Toast.LENGTH_LONG).show()
            }
    }
}