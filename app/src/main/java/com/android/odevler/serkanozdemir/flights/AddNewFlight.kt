package com.android.odevler.serkanozdemir.flights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityAddNewFlightBinding
import com.android.odevler.serkanozdemir.data.Flight
import com.google.firebase.firestore.FirebaseFirestore

class AddNewFlight : AppCompatActivity() {

    private var bindingFlight: ActivityAddNewFlightBinding?=null
    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingFlight = DataBindingUtil.setContentView(this,R.layout.activity_add_new_flight)
        setContentView(R.layout.activity_add_new_flight)
        bindingFlight?.saveButton?.setOnClickListener{
            if (isValid()) {
                save()
            }
        }
        firestore = FirebaseFirestore.getInstance()

    }

    private fun save() {
        val flight = Flight(flightNumber = bindingFlight?.edtflightNumber?.text.toString(),
            from = bindingFlight?.edtfrom?.text.toString(),
            to = bindingFlight?.edtto?.text.toString(),
            departureTime = bindingFlight?.edtdepartureTime?.text.toString(),
            landingTime = bindingFlight?.edtlandingTime?.text.toString())
        firestore?.collection("serkanozdemir")?.add(flight)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Flight not added...", Toast.LENGTH_LONG).show()
            }
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
            bindingFlight?.edtflightNumber,
            bindingFlight?.edtfrom,
            bindingFlight?.edtto,
            bindingFlight?.edtdepartureTime,
            bindingFlight?.edtlandingTime
        ).forEach { editText ->
            isValid = editText?.isValid() == true && isValid
        }

        return isValid
    }
}