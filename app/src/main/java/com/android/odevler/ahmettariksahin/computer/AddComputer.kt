package com.android.odevler.ahmettariksahin.computer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.ahmettariksahin.model.Computer
import com.google.firebase.firestore.FirebaseFirestore

class AddComputer : AppCompatActivity() {
    private val powerSupply: EditText by lazy { findViewById(R.id.power) }
    private val screenModel: EditText by lazy { findViewById(R.id.screen) }
    private val motherBoard: EditText by lazy { findViewById(R.id.motherBoard) }
    private val ram: EditText by lazy { findViewById(R.id.ram) }
    private val keyboard: EditText by lazy { findViewById(R.id.keyboard) }
    private val addButton by lazy { findViewById<Button>(R.id.add) }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_computer)
        addButton.setOnClickListener{
            if (isValid()) {
                save()
            }
        }
        firestore = FirebaseFirestore.getInstance()

    }

    private fun save() {
        val computer = Computer(powerSupply = powerSupply.text.toString(),
            screenModel = screenModel.text.toString(),
            motherBoard = motherBoard.text.toString(),
            ram = ram.text.toString(),
            keyboard = keyboard.text.toString())
        firestore?.collection("ahmettariksahin")?.add(computer)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Pc not added:(", Toast.LENGTH_LONG).show()
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
            powerSupply,
            screenModel,
            motherBoard,
            ram,
            keyboard
        ).forEach { editText ->
            isValid = editText.isValid() && isValid
        }

        return isValid
    }
}
