package com.android.odevler.mehmet_serkan_guzel.language

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.android.camp.R
import com.android.odevler.mehmet_serkan_guzel.data.Language
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class AddNewLanguageActivity : AppCompatActivity() {
    private val buttonSaveLanguage by lazy {
        findViewById<View>(R.id.button_save_language)
    }
    private val editTextLanguageName by lazy {
        findViewById<EditText>(R.id.languageNameEditText_input_layout)
    }
    private val textInputLayoutLanguageName by lazy {
        findViewById<TextInputLayout>(R.id.languageNameText_input_layout)
    }
    private val editTextCode by lazy {
        findViewById<EditText>(R.id.codeEditTextInputLayout)
    }
    private val textInputLayoutCode by lazy {
        findViewById<TextInputLayout>(R.id.codeTextInputLayout)
    }
    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_language)

        textInputLayoutLanguageName.changeListener()
        textInputLayoutCode.changeListener()

        buttonSaveLanguage.setOnClickListener {
            if(isValid()){
                save()
            }
        }

        firestore = FirebaseFirestore.getInstance()
    }

    private fun save() {
        val language =
            Language(
               languageName = editTextLanguageName.text.toString(),
                languageCode = editTextCode.text.toString()
            )

        firestore?.collection("languages")?.add(language)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> {
                    Toast.makeText(this, "${editTextLanguageName.text} Dili Başarıyla Eklendi.", Toast.LENGTH_LONG).show()
                }
                false -> Toast.makeText(this, "Dil Eklenemedi...", Toast.LENGTH_LONG).show()
            }

        }
        println(language.languageName + language.languageCode)
    }
    private fun TextInputLayout.changeListener() {
        editText!!.addTextChangedListener {
            if(!it.isNullOrEmpty()) {
                this.isErrorEnabled = false
            }
        }
    }
    private fun TextInputLayout.isValid(counterMaxLength : Int): Boolean {
        val errorHint = hint.toString()
        if(editText!!.text.isNullOrEmpty()) {
            error = "${errorHint.replace(".." ,"").replace("?","")} boş olamaz!"
            requestFocus()
            return false
        }
        if(editText!!.text.length > counterMaxLength) {
            error = "Maximum karakter aşıldı!"
            requestFocus()
            return false

        }
        return true
    }
    private fun isValid(): Boolean {
        var isValid = true
        arrayListOf(
            textInputLayoutCode,
            textInputLayoutLanguageName
        ).forEach { textInputLayout ->
            isValid = textInputLayout.isValid(textInputLayout.counterMaxLength) && isValid
        }
        return isValid
    }

}