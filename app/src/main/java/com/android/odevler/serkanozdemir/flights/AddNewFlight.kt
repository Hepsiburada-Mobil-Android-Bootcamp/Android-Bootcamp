package com.android.odevler.serkanozdemir.flights

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.serkanozdemir.data.Flight
import com.google.firebase.firestore.FirebaseFirestore

class AddNewFlight : AppCompatActivity() {
    val edtFlightNumber by lazy { findViewById<EditText>(R.id.edtflightNumber) }
    val edtFrom by lazy { findViewById<EditText>(R.id.edtfrom) }
    val edtTo by lazy { findViewById<EditText>(R.id.edtto) }
    val edtDepartureTime by lazy { findViewById<EditText>(R.id.edtdepartureTime) }
    val edtLandingTime by lazy { findViewById<EditText>(R.id.edtlandingTime) }
    val saveButton by lazy { findViewById<Button>(R.id.saveButton) }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_flight)
        saveButton.setOnClickListener{
            if (isValid()) {
                save()
            }
        }
        firestore = FirebaseFirestore.getInstance()

    }

    private fun save() {
        val flight = Flight(flightNumber = edtFlightNumber.text.toString(),
            from = edtFrom.text.toString(),
            to = edtTo.text.toString(),
            departureTime = edtDepartureTime.text.toString(),
            landingTime = edtLandingTime.text.toString())
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
            edtFlightNumber,
            edtFrom,
            edtTo,
            edtDepartureTime,
            edtLandingTime
        ).forEach { editText ->
            isValid = editText.isValid() && isValid
        }

        return isValid
    }
}