package com.android.camp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class AddNewQuestionActivity : AppCompatActivity() {
    private val editTextQuestion by lazy { findViewById<EditText>(R.id.edit_text_question) }
    private val editTextA by lazy { findViewById<EditText>(R.id.edit_text_a) }
    private val editTextB by lazy { findViewById<EditText>(R.id.edit_text_b) }
    private val editTextC by lazy { findViewById<EditText>(R.id.edit_text_c) }
    private val editTextD by lazy { findViewById<EditText>(R.id.edit_text_d) }
    private val editTextE by lazy { findViewById<EditText>(R.id.edit_text_e) }
    private val radioGroup by lazy { findViewById<RadioGroup>(R.id.radio_group) }
    private val buttonSave by lazy { findViewById<View>(R.id.button_save) }

    private var secilenCevap:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_question)

        buttonSave.setOnClickListener {
            if(isValid()) {
                save()
            }
        }

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            secilenCevap = when(i) {
                R.id.radio_group_a -> "A"
                R.id.radio_group_b -> "B"
                R.id.radio_group_c -> "C"
                R.id.radio_group_d -> "D"
                R.id.radio_group_e -> "E"
                else -> null
            }

            Log.d("AddNewQuestionActivity", "seçilen şık: $secilenCevap")
        }

    }

    private fun save() {
        Log.d("AddNewQuestionActivity", "valid form.... şu an kaydedilebilirrrrr")
    }

    fun EditText.isValid(): Boolean {
        if(text.isNullOrEmpty()) {
            requestFocus()
            error  = "bu alan gereklii.."
        }

        return text.isNotEmpty()
    }

    private fun isValid(): Boolean {
        var isValid = true

        isValid = editTextQuestion.isValid() || editTextA.isValid() || editTextB.isValid() || editTextC.isValid()
                || editTextD.isValid() || editTextE.isValid()

        if(secilenCevap.isNullOrEmpty()) {
            Toast.makeText(this, "doğru olan şık seçilmelidir.", Toast.LENGTH_LONG).show()
            isValid = false
        }

        return  isValid
    }
}