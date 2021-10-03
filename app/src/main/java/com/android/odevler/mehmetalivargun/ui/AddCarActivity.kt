package com.android.odevler.mehmetalivargun.ui

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
import com.android.odevler.mehmetalivargun.data.Car
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddCarActivity : AppCompatActivity() {
    private val editTextBrand by lazy { findViewById<TextInputLayout>(R.id.brandName) }
    private val editTextModel by lazy { findViewById<TextInputLayout>(R.id.modelName) }
    private val editTextModelYear by lazy { findViewById<TextInputLayout>(R.id.modelYear) }
    private val editTextMaxSpeed by lazy { findViewById<TextInputLayout>(R.id.maxSpeed) }
    private val buttonSave by lazy { findViewById<View>(R.id.button_save) }
    private var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)
        firestore = FirebaseFirestore.getInstance()
        buttonSave.setOnClickListener {
            if(isValid()){
                Log.e("Here","Here")
                save()
            }
        }
    }

    private fun save() {
        Log.d("AddNewQuestionActivity", "valid form.... şu an kaydedilebilirrrrr")

        val car= Car(
            brand= editTextBrand.editText?.text.toString(),
            model= editTextModel.editText?.text.toString(),
            modelYear = editTextModelYear.editText?.text.toString().toInt(),
            maxSpeed = editTextMaxSpeed.editText?.text.toString().toInt(),
            gasCapacity = 100

        )

        firestore?.collection("mehmetalivargun")?.add(car)
            ?.addOnSuccessListener {
                finish()
            }
            ?.addOnFailureListener {
                Toast.makeText(this, "Yeni Araba Eklenemedi.", Toast.LENGTH_LONG).show()
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
            editTextBrand.editText,
            editTextModel.editText,
            editTextModelYear.editText,
            editTextMaxSpeed.editText
        ).forEach { editText ->
            isValid = editText!!.isValid() && isValid
        }


        return isValid
    }
}