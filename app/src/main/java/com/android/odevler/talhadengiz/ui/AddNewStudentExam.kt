package com.android.odevler.talhadengiz.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.camp.R
import com.android.odevler.talhadengiz.data.model.StudentExam
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class AddNewStudentExam : AppCompatActivity() {

    private val edtStudentNumber: TextInputEditText by lazy { findViewById(R.id.edt_student_number) }
    private val edtlessonName: TextInputEditText by lazy { findViewById(R.id.edt_lesson_name) }
    private val edtMidtermPoint: TextInputEditText by lazy { findViewById(R.id.edt_midterm_point) }
    private val edtFinalPoint: TextInputEditText by lazy { findViewById(R.id.edt_final_point) }

    private val btnSave: Button by lazy { findViewById(R.id.btn_save) }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student_exam)

        initClickListener()
        firestore = FirebaseFirestore.getInstance()
    }

    private fun initClickListener() {
        btnSave.setOnClickListener {
            if (validate()) {
                save()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.tum_alanlar_doldurulmalidir),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun validate(): Boolean {
        return !(edtStudentNumber.text.isNullOrEmpty() ||
                edtlessonName.text.isNullOrEmpty() ||
                edtMidtermPoint.text.isNullOrEmpty() ||
                edtFinalPoint.text.isNullOrEmpty())
    }

    private fun save() {
        val studentExam = StudentExam(
            studentNumber = edtStudentNumber.text.toString().toInt(),
            lessonName = edtlessonName.text.toString(),
            midtermPoint = edtMidtermPoint.text.toString().toDouble(),
            finalPoint = edtFinalPoint.text.toString().toDouble()
        )

        firestore?.collection(resources.getString(R.string.collection_name))?.add(studentExam)
            ?.addOnSuccessListener {
                Toast.makeText(this, resources.getString(R.string.successful), Toast.LENGTH_LONG).show()
                finish()
            }?.addOnFailureListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}