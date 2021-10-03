package com.android.odevler.tahaarican.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.camp.data.model.Exam
import com.android.odevler.tahaarican.data.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddNewUserActivity : AppCompatActivity() {
    private val userbtnSave by lazy { findViewById<View>(R.id.user_button_save) }
    private val edtName by lazy { findViewById<EditText>(R.id.edt_user_name) }
    private val edtsurname by lazy { findViewById<EditText>(R.id.edt_user_surname) }
    private val userNameLayout by lazy { findViewById<TextInputLayout>(R.id.user_name_layout) }
    private val userSurnameLayout by lazy { findViewById<TextInputLayout>(R.id.user_surname_layout) }

    private var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_user)

        userbtnSave.setOnClickListener {
            if (valid()) {
                save()
            }
        }

        firestore = FirebaseFirestore.getInstance()
    }

    private fun valid(): Boolean {
        val name = edtName.text
        if (name.isNullOrEmpty()) {
            edtName.requestFocus()
            userNameLayout.error = "Kullanıcı adı boş olamaz."
            return false
        }
        userNameLayout.isErrorEnabled = false
        return true
    }

    private fun save() {
        val user =
            User(name = edtName.text.toString(),surname = edtsurname.text.toString(), createdDate = Calendar.getInstance().time.time, password = null, username = null, id = null)

        firestore?.collection("tahaarican")?.add(user)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Kullanıcı Eklenemedi...", Toast.LENGTH_LONG).show()
            }
        }
    }
}