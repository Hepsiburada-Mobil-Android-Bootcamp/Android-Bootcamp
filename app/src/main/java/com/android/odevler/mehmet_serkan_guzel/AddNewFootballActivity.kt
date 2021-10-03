package com.android.odevler.mehmet_serkan_guzel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.android.camp.R
import com.android.odevler.mehmet_serkan_guzel.data.Football
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class AddNewFootballActivity : AppCompatActivity() {

    private val buttonFootballSave by lazy {
        findViewById<View>(R.id.button_save_football)
    }
    private val editTextFootballName by lazy {
        findViewById<EditText>(R.id.teamNameEditText_input_layout)
    }
    private val textInputLayoutFootballName by lazy {
        findViewById<TextInputLayout>(R.id.teamNameText_input_layout)
    }

    private val editTextYear by lazy {
        findViewById<EditText>(R.id.yearTextInputEditText)
    }

    private val textInputLayoutYear by lazy {
        findViewById<TextInputLayout>(R.id.yearTextInputLayout)
    }

    private val editTextBestPlayer by lazy {
        findViewById<EditText>(R.id.bestPlayerEditTextInputLayout)
    }
    private val textInputLayoutBestPlayer by lazy {
        findViewById<TextInputLayout>(R.id.bestPlayerTextInputLayout)
    }
    private val editTextTeamColor by lazy {
        findViewById<EditText>(R.id.teamColorEditTextInputLayout)
    }
    private val textInputLayoutTeamColor by lazy {
        findViewById<TextInputLayout>(R.id.teamColorTextInputLayout)
    }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_football)

        textInputLayoutFootballName.changeListener()
        textInputLayoutYear.changeListener()
        textInputLayoutBestPlayer.changeListener()
        textInputLayoutTeamColor.changeListener()

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
                year = editTextYear.text.toString().toInt(),
                bestPlayer = editTextBestPlayer.text.toString(),
                teamColor = editTextTeamColor.text.toString()
            )

        firestore?.collection("MehmetSerkanGuzel")?.add(football)?.addOnCompleteListener { task ->
           when (task.isSuccessful) {
               true -> {
                   Log.d("AddNewFootballActivity", "Takım Başarıyla Eklendi..")
                   finish()
               }
               false -> Toast.makeText(this, "Takım Eklenemedi...", Toast.LENGTH_LONG).show()
           }

        }

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
            textInputLayoutTeamColor,
            textInputLayoutBestPlayer,
            textInputLayoutYear,
            textInputLayoutFootballName
            ).forEach { textInputLayout ->
            isValid = textInputLayout.isValid(textInputLayout.counterMaxLength) && isValid
        }
        return isValid
    }
}