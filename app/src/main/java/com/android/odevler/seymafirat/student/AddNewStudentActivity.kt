package com.android.odevler.seymafirat.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.seymafirat.data.Student
import com.google.firebase.firestore.FirebaseFirestore

class AddNewStudentActivity : AppCompatActivity() {
    private val buttonKaydet by lazy {findViewById<View>(R.id.button_kaydet)}
    private val editTextStudentName by lazy {findViewById<EditText>(R.id.edit_text_student_name)}
    private val editTextStudentSurname by lazy {findViewById<EditText>(R.id.edit_text_student_surname)}
    private val editTextStudentNumber by lazy {findViewById<EditText>(R.id.edit_text_student_number)}
    private val editTextStudentClass by lazy {findViewById<EditText>(R.id.edit_text_class_number)}

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)

        buttonKaydet.setOnClickListener{
            if(isValid()){
                save()
            }
        }
        firestore = FirebaseFirestore.getInstance()
    }


    fun EditText.isValid(): Boolean {
        if (text.isNullOrEmpty()) {
            requestFocus()
            error = "bu alan gerekli.."
        }

        return text.isNotEmpty()
    }

    private fun isValid(): Boolean {
        var isValid: Boolean

        isValid = editTextStudentName.isValid()
        isValid = editTextStudentSurname.isValid()
        isValid = editTextStudentNumber.isValid()
        isValid = editTextStudentClass.isValid()


        return isValid
    }
    private fun save(){
        val student = Student(
            name = editTextStudentName.text.toString(),
            surname = editTextStudentSurname.text.toString(),
            studentNumber = editTextStudentClass.text.toString().toInt(),
            classNumber = editTextStudentClass.text.toString().toInt())
            firestore?.collection("seymafirat")?.add(student)?.addOnCompleteListener{task ->
                when(task.isSuccessful){
                    true -> finish()
                    false -> Toast.makeText(this, "Öğrenci eklenemedi.", Toast.LENGTH_LONG).show()
                }
            }
    }
}