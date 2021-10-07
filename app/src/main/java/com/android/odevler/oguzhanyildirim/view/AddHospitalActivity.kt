package com.android.odevler.oguzhanyildirim.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityAddHospitalBinding
import com.android.odevler.oguzhanyildirim.data.model.Hospital
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class AddHospitalActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null
    private var addHospitalBinding : ActivityAddHospitalBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addHospitalBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_add_hospital
        )
        firestore = FirebaseFirestore.getInstance()

        addHospitalBinding?.btnSave?.setOnClickListener {
            if (inputsValid()) {
                save()
            }
        }
    }

    private fun save() {
        addHospitalBinding?.let { bindingObject ->
            val newHospital = Hospital(
                bindingObject.inputHospitalName.getInput(),
                bindingObject.inputHospitalAddress.getInput(),
                bindingObject.inputHospitalContact.getInput(),
                bindingObject.inputHospitalCapacity.getInput()
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


    }

    private fun inputsValid(): Boolean {
        addHospitalBinding?.let{ bindingObject ->
            val name = bindingObject.inputHospitalName.getInput()
            val address = bindingObject.inputHospitalAddress.getInput()
            val contact = bindingObject.inputHospitalContact.getInput()
            val capacity = bindingObject.inputHospitalCapacity.getInput()

            when {
                name.isEmpty() -> {
                    bindingObject.inputHospitalName.requestFocus()
                    bindingObject.layoutHospitalName.error = "Name can not be empty.."

                    return false
                }
                else -> {
                    bindingObject.layoutHospitalName.isErrorEnabled = false
                }
            }

            when {
                address.isEmpty() -> {
                    bindingObject.inputHospitalAddress.requestFocus()
                    bindingObject.layoutHospitalAddress.error = "Address can not be empty.."

                    return false
                }
                else -> {
                    bindingObject.layoutHospitalAddress.isErrorEnabled = false
                }
            }

            when {
                contact.isEmpty() -> {
                    bindingObject.inputHospitalContact.requestFocus()
                    bindingObject.layoutHospitalContact.error = "Contact can not be empty.."

                    return false
                }
                else -> {
                    bindingObject.layoutHospitalContact.isErrorEnabled = false
                }
            }

            when {
                capacity.isEmpty() -> {
                    bindingObject.inputHospitalCapacity.requestFocus()
                    bindingObject.layoutHospitalCapacity.error = "Capacity can not be empty.."

                    return false
                }
                else -> {
                    bindingObject.layoutHospitalCapacity.isErrorEnabled = false
                }
            }
            return true
        }
        return false

    }


    private fun EditText.getInput(): String {
        return this.text.toString()
    }
}