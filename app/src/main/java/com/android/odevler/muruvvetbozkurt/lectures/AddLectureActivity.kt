package com.android.odevler.muruvvetbozkurt.lectures

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.camp.R
import com.android.odevler.muruvvetbozkurt.data.lectures
import com.google.firebase.firestore.FirebaseFirestore

class AddLectureActivity() : AppCompatActivity() {

    private val buttonSave by lazy { findViewById<View>(R.id.button_add_lecture) }
    private val editCrn by lazy { findViewById<EditText>(R.id.edit_crn) }
    private val editCourseCode by lazy { findViewById<EditText>(R.id.edit_coursecode) }
    private val editTitle by lazy { findViewById<EditText>(R.id.edit_title) }
    private val editInstructor by lazy { findViewById<EditText>(R.id.edit_instructor) }
    private val editCapacity by lazy { findViewById<EditText>(R.id.edit_capacity) }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_exam)

        buttonSave.setOnClickListener {
            if (isValid()) {
                saveLecture()
            }
        }

        firestore = FirebaseFirestore.getInstance()
    }

    private fun isValid(): Boolean {
        val crn = editCrn.text
        val courseCode = editCourseCode.text
        val title = editTitle.text
        val instructor = editInstructor.text
        val capacity = editCapacity.text
        if (crn.isNullOrEmpty() || courseCode.isNullOrEmpty() || title.isNullOrEmpty() || capacity.isNullOrEmpty()) {
            editCrn.requestFocus()
            editCrn.error = "This area cannot be empty!"
            return false
        } else if (crn.length < 4) {
            editCrn.requestFocus()
            editCrn.error = "Length of Crn cannot be smaller than 4!"
            return false
        }
        return true
    }

    private fun saveLecture() {
        val lecture =
            lectures(crn = editCrn.text.toString().toInt(), courseCode = editCourseCode.text.toString(), title= editTitle.text.toString(), instructor = editInstructor.text.toString(), capacity = editCapacity.text.toString().toInt())

        firestore?.collection("muruvvetbozkurt")?.add(lecture)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Lecture was not added!", Toast.LENGTH_LONG).show()
            }
        }
    }

}