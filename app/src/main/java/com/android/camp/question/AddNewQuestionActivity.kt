package com.android.camp.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.android.camp.R
import com.android.camp.data.CampHelper
import com.android.camp.data.model.Answer
import com.android.camp.data.model.Question
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore

class AddNewQuestionActivity : AppCompatActivity() {
    private val editTextQuestion by lazy { findViewById<EditText>(R.id.edit_text_question) }
    private val editTextA by lazy { findViewById<EditText>(R.id.edit_text_a) }
    private val editTextB by lazy { findViewById<EditText>(R.id.edit_text_b) }
    private val editTextC by lazy { findViewById<EditText>(R.id.edit_text_c) }
    private val editTextD by lazy { findViewById<EditText>(R.id.edit_text_d) }
    private val editTextE by lazy { findViewById<EditText>(R.id.edit_text_e) }
    private val radioGroup by lazy { findViewById<RadioGroup>(R.id.radio_group) }
    private val buttonSave by lazy { findViewById<View>(R.id.button_save) }

    private var secilenCevap: String? = null

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_question)

        buttonSave.setOnClickListener {
            if (isValid()) {
                save()
            }
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            secilenCevap = when (i) {
                R.id.radio_group_a -> "A"
                R.id.radio_group_b -> "B"
                R.id.radio_group_c -> "C"
                R.id.radio_group_d -> "D"
                R.id.radio_group_e -> "E"
                else -> null
            }

            Log.d("AddNewQuestionActivity", "seçilen şık: $secilenCevap")
        }

        firestore = FirebaseFirestore.getInstance()
    }

    private fun save() {
        Log.d("AddNewQuestionActivity", "valid form.... şu an kaydedilebilirrrrr")

        val question = Question(
            question = editTextQuestion.text.toString(),
            answers = arrayListOf(
                Answer(type = "A", answer = editTextA.text.toString()),
                Answer(type = "B", answer = editTextB.text.toString()),
                Answer(type = "C", answer = editTextC.text.toString()),
                Answer(type = "D", answer = editTextD.text.toString()),
                Answer(type = "E", answer = editTextE.text.toString())
            ),
            correctAnswer = secilenCevap ?: ""
        )

        CampHelper.list.add(question)

        firestore?.collection("questions")?.add(question)
            ?.addOnSuccessListener {
                finish()
            }
            ?.addOnFailureListener {
                Toast.makeText(this, "Yeni Soru Eklenemedi..", Toast.LENGTH_LONG).show()
            }
    }

    fun EditText.isValid(): Boolean {
        if (text.isNullOrEmpty()) {
            requestFocus()
            error = "bu alan gereklii.."
        }

        return text.isNotEmpty()
    }

    private fun isValid(): Boolean {
        var isValid: Boolean

        isValid = editTextQuestion.isValid()
        isValid = editTextA.isValid()
        isValid = editTextB.isValid()
        isValid = editTextC.isValid()
        isValid = editTextD.isValid()
        isValid = editTextE.isValid()

        if (secilenCevap.isNullOrEmpty()) {
            Toast.makeText(this, "doğru olan şık seçilmelidir.", Toast.LENGTH_LONG).show()
            isValid = false
        }

        return isValid
    }
}