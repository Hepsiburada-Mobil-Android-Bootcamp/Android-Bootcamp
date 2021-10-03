package com.android.odevler.oguzhanyildirim.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.oguzhanyildirim.data.model.Hospital
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class AddHospitalActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null
    private val buttonSave by lazy { findViewById<View>(R.id.btnSave) }
    private val inputName by lazy { findViewById<EditText>(R.id.inputHospitalName) }
    private val inputAddress by lazy { findViewById<EditText>(R.id.inputHospitalAddress) }
    private val inputContact by lazy { findViewById<EditText>(R.id.inputHospitalContact) }
    private val inputCapacity by lazy { findViewById<EditText>(R.id.inputHospitalCapacity) }

    private val inputNameLayout by lazy { findViewById<TextInputLayout>(R.id.layoutHospitalName) }
    private val inputAddressLayout by lazy { findViewById<TextInputLayout>(R.id.layoutHospitalAddress) }
    private val inputContactLayout by lazy { findViewById<TextInputLayout>(R.id.layoutHospitalContact) }
    private val inputCapacityLayout by lazy { findViewById<TextInputLayout>(R.id.layoutHospitalCapacity) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hospital)
        firestore = FirebaseFirestore.getInstance()

        buttonSave.setOnClickListener {
            if (inputsValid()) {
                save()
            }
        }
    }

    private fun save() {
        val newHospital = Hospital(
            inputName.getInput(),
            inputAddress.getInput(),
            inputContact.getInput(),
            inputCapacity.getInput()
        )
        Log.i("ERROR", "Save Called")
        firestore?.collection("oguzhanyildirim")?.add(newHospital)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> {
                    finish()
                    Log.i("ERROR","Success True")
                }

                false -> {
                    Log.i("ERROR","Success False")
                    Toast.makeText(this, "An error occured.", Toast.LENGTH_LONG).show()
                }
            }
        }?.addOnFailureListener {
            Log.i("ERROR","Failure")
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun inputsValid(): Boolean {
        val name = inputName.getInput()
        val address = inputAddress.getInput()
        val contact = inputContact.getInput()
        val capacity = inputCapacity.getInput()

        when {
            name.isEmpty() -> {
                inputName.requestFocus()
                inputNameLayout.error = "Name can not be empty.."

                return false
            }
            else -> {
                inputNameLayout.isErrorEnabled = false
            }
        }

        when {
            address.isEmpty() -> {
                inputAddress.requestFocus()
                inputAddressLayout.error = "Address can not be empty.."

                return false
            }
            else -> {
                inputAddressLayout.isErrorEnabled = false
            }
        }

        when {
            contact.isEmpty() -> {
                inputContact.requestFocus()
                inputContactLayout.error = "Contact can not be empty.."

                return false
            }
            else -> {
                inputContactLayout.isErrorEnabled = false
            }
        }

        when {
            capacity.isEmpty() -> {
                inputCapacity.requestFocus()
                inputCapacityLayout.error = "Capacity can not be empty.."

                return false
            }
            else -> {
                inputCapacityLayout.isErrorEnabled = false
            }
        }
        return true
    }


    private fun EditText.getInput(): String {
        return this.text.toString()
    }
}