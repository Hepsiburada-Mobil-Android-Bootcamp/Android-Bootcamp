package com.android.camp.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.camp.data.model.Exam
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddNewExamActivity : AppCompatActivity() {

    private val buttonSave by lazy { findViewById<View>(R.id.button_save) }
    private val editTextExamName by lazy { findViewById<EditText>(R.id.edit_text_name) }
    private val textInputLayoutExamName by lazy { findViewById<TextInputLayout>(R.id.text_input_layout) }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_exam)

        buttonSave.setOnClickListener {
            if (valid()) {
                save()
            }
        }

        firestore = FirebaseFirestore.getInstance()
    }

    private fun valid(): Boolean {
        val name = editTextExamName.text
        if (name.isNullOrEmpty()) {
            editTextExamName.requestFocus()
            textInputLayoutExamName.error = "Sınav ismi boş olamaz."

            return false
        } else if (name.length < 5) {
            editTextExamName.requestFocus()
            textInputLayoutExamName.error = "Sınav ismi 5 karakterden küçük olamaz."

            return false
        }

        textInputLayoutExamName.isErrorEnabled = false
        return true
    }

    private fun save() {
        val exam =
            Exam(date = Calendar.getInstance().time.time, name = editTextExamName.text.toString())

        firestore?.collection("exam")?.add(exam)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Sınav Eklenemedi...", Toast.LENGTH_LONG).show()
            }
        }
    }
}