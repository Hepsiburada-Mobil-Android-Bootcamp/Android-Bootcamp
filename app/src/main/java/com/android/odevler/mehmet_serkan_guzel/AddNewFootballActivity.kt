package com.android.odevler.mehmet_serkan_guzel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.android.camp.R
import com.android.odevler.mehmet_serkan_guzel.data.Football
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class AddNewFootballActivity : AppCompatActivity() {
    private val buttonFootballSave by lazy {
        findViewById<View>(R.id.button_save_football)
    }
    private val editTextFootballName by lazy {
        findViewById<EditText>(R.id.footballeditText_input_layout)
    }
    private val textInputLayoutFootballName by lazy {
        findViewById<TextInputLayout>(R.id.footballText_input_layout)
    }
    private var firestore: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_football)

        editTextFootballName.addTextChangedListener { teamName ->
            if(!teamName.isNullOrEmpty()) {
                textInputLayoutFootballName.isErrorEnabled = false
            }
        }
        buttonFootballSave.setOnClickListener {
            if(isValid()){
                save()
            }
        }
       firestore = FirebaseFirestore.getInstance()
    }

    private fun save() {
        val football =
            Football(
                teamName = editTextFootballName.text.toString(),
            )
          println("Takım adı :" +  "${football.teamName}")

    }

    private fun isValid(): Boolean {
        return when {
            editTextFootballName.text.isNullOrEmpty()  -> {
                editTextFootballName.requestFocus()
                textInputLayoutFootballName.error = "Takım ismi boş olamaz!"
                false
            }

            editTextFootballName.text.length < 3 -> {
                editTextFootballName.requestFocus()
                textInputLayoutFootballName.error = "Takım ismi 3 karakterden küçük olamaz!"
                false
            }
            else ->  {
                true
            }
        }
    }
}