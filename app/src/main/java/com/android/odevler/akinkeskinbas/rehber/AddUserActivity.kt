package com.android.odevler.akinkeskinbas.rehber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.akinkeskinbas.data.model.Users
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddUserActivity : AppCompatActivity() {
    val addNewUser by lazy { findViewById<View>(R.id.addBtn) }
    private var firestore: FirebaseFirestore? = null
    private val editTextName by lazy { findViewById<EditText>(R.id.nameText) }
    private val editTextMail by lazy { findViewById<EditText>(R.id.mailText) }
    private val editTextNumber by lazy { findViewById<EditText>(R.id.numberText) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        firestore = FirebaseFirestore.getInstance()

        addNewUser.setOnClickListener {
            if (isValid()){
                addUsertoFirebase()

            }

        }
    }
        private fun addUsertoFirebase() {
            val newUser = Users(
                name = editTextName.text.toString(),
                mail = editTextMail.text.toString(),
                number = editTextNumber.text.toString().toInt(),
                yetkiliMi = false,
                profilUrl = "test",
                date = Calendar.getInstance().time.time

            )
           // println(editTextNumber.text.toString().toInt())
        firestore?.collection("akinkeskinbas")?.add(newUser)
            ?.addOnSuccessListener {
                finish()
            }
            ?.addOnFailureListener {
                Toast.makeText(this, "Yeni Kullanici Eklenemedi..", Toast.LENGTH_LONG).show()
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
            editTextName,
            editTextMail,
            editTextNumber,

        ).forEach { editText ->
            isValid = editText.isValid() && isValid
        }



        return isValid
    }
}