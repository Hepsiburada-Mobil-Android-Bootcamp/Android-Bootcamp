package com.android.odevler.mehmetalivargun.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.data.CampHelper
import com.android.camp.data.model.Answer
import com.android.camp.data.model.Question
import com.android.camp.databinding.ActivityAddCarBinding
import com.android.odevler.mehmetalivargun.data.Car
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddCarActivity : AppCompatActivity() {
    private var binding: ActivityAddCarBinding? = null

    private var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_car)
        firestore = FirebaseFirestore.getInstance()
        binding?.buttonSave?.setOnClickListener {
            if(isValid()){
                Log.e("Here","Here")
                save()
            }
        }
    }

    private fun save() {
        Log.d("AddNewQuestionActivity", "valid form.... şu an kaydedilebilirrrrr")

        val car= Car(
            brand= binding?.brandName?.editText?.text.toString(),
            model= binding?.modelName?.editText?.text.toString(),
            modelYear = binding?.modelYear?.editText?.text.toString().toInt(),
            maxSpeed = binding?.maxSpeed?.editText?.text.toString().toInt(),
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
            binding?.brandName?.editText,
            binding?.modelName?.editText,
            binding?.modelYear?.editText,
            binding?.maxSpeed?.editText
        ).forEach { editText ->
            isValid = editText!!.isValid() && isValid
        }


        return isValid
    }
}