package com.android.camp.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.data.CampHelper
import com.android.camp.data.model.Answer
import com.android.camp.data.model.Question
import com.android.camp.databinding.ActivityAddNewQuestionBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddNewQuestionActivity : AppCompatActivity() {
    private var secilenCevap: String? = null
    private var examId: String? = null
    private var firestore: FirebaseFirestore? = null
    private var binding: ActivityAddNewQuestionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_question)

        binding?.buttonSave?.setOnClickListener {
            if (isValid()) {
                save()
            }
        }

        binding?.radioGroup?.setOnCheckedChangeListener { radioGroup, i ->
            secilenCevap = when (i) {
                R.id.radio_group_a -> "A"
                R.id.radio_group_b -> "B"
                R.id.radio_group_c -> "C"
                R.id.radio_group_d -> "D"
                else -> null
            }

            Log.d("AddNewQuestionActivity", "seçilen şık: $secilenCevap")
        }

        examId = intent.getStringExtra("EXAM_ID")
        firestore = FirebaseFirestore.getInstance()
    }

    private fun save() {
        Log.d("AddNewQuestionActivity", "valid form.... şu an kaydedilebilirrrrr")

        val question = Question(
            question = binding?.editTextQuestion?.text.toString(),
            answers = arrayListOf(
                Answer(type = "A", answer = binding?.editTextA?.text.toString()),
                Answer(type = "B", answer = binding?.editTextB?.text.toString()),
                Answer(type = "C", answer = binding?.editTextC?.text.toString()),
                Answer(type = "D", answer = binding?.editTextD?.text.toString())
            ),
            correctAnswer = secilenCevap ?: "",
            date = Calendar.getInstance().time.time
        )

        CampHelper.list.add(question)

        firestore?.collection("exam")?.document(examId.toString())?.collection("questions")
            ?.add(question)
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
        var isValid = true

        arrayListOf(
            binding?.editTextQuestion,
            binding?.editTextA,
            binding?.editTextB,
            binding?.editTextC,
            binding?.editTextD
        ).forEach { editText ->
            isValid = editText?.isValid() == true && isValid
        }

        if (secilenCevap.isNullOrEmpty()) {
            Toast.makeText(this, "doğru olan şık seçilmelidir.", Toast.LENGTH_LONG).show()
            isValid = false
        }

        return isValid
    }
}