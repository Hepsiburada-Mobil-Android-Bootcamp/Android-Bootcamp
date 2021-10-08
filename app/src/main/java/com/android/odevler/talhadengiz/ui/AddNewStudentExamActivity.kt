package com.android.odevler.talhadengiz.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityAddNewStudentExamBinding
import com.android.odevler.talhadengiz.data.model.StudentExam
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class AddNewStudentExamActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null

    private var binding : ActivityAddNewStudentExamBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_student_exam)

        initClickListener()
        firestore = FirebaseFirestore.getInstance()
    }

    private fun initClickListener() {
        binding?.btnSave?.setOnClickListener {
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
        return !(binding?.edtStudentNumber?.text.isNullOrEmpty() ||
                binding?.edtLessonName?.text.isNullOrEmpty() ||
                binding?.edtMidtermPoint?.text.isNullOrEmpty() ||
                binding?.edtFinalPoint?.text.isNullOrEmpty())
    }

    private fun save() {
        val studentExam = StudentExam(
            studentNumber = binding?.edtStudentNumber?.text.toString().toInt(),
            lessonName = binding?.edtLessonName?.text.toString(),
            midtermPoint = binding?.edtMidtermPoint?.text.toString().toDouble(),
            finalPoint = binding?.edtFinalPoint?.text.toString().toDouble()
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