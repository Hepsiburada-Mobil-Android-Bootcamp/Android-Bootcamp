package com.android.odevler.nahitfidanci.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.nahitfidanci.data.Galaxy
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class AddNewGalaxyActivity : AppCompatActivity() {
    private val saveButton by lazy { findViewById<Button>(R.id.saveNewGalaxy) }

    private val name by lazy { findViewById<EditText>(R.id.newGalaxyName) }
    private val stars by lazy { findViewById<EditText>(R.id.newGalaxyKnownNumOfStars) }
    private val year by lazy { findViewById<EditText>(R.id.newGalaxyExploredYear) }
    private val distance by lazy { findViewById<EditText>(R.id.newGalaxyLightYearDistance) }


    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_galaxy)
        firestore = FirebaseFirestore.getInstance()


        saveButton.setOnClickListener {
            if (valid()) {
                saveGalaxy()
            }
        }
    }

    private fun saveGalaxy() {

        val galaxy = Galaxy(
            name.text.toString(),
            distance.text.toString().toInt(),
            stars.text.toString().toLong(),
            year.text.toString().toInt()
        )
        firestore?.collection("nahitfidanci")?.add(galaxy)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> {
                    finish()
                }
                false -> {
                    Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show()
                }
            }
        }?.addOnFailureListener {
            Log.e("FireStore ERROR", it.message.toString())
            Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show()

        }
    }

    private fun valid(): Boolean {
        val name = name.text
        val year = year.text
        val distance = distance.text
        val stars = stars.text

        return if (!name.isNullOrEmpty()
            && !year.isNullOrEmpty()
            && !distance.isNullOrEmpty()
            && !stars.isNullOrEmpty()
        ) {


            true
        } else {
            Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show()

            false
        }


    }
}