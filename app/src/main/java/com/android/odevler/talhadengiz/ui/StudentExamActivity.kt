package com.android.odevler.talhadengiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.talhadengiz.data.model.StudentExam
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class StudentExamActivity : AppCompatActivity() {

    private val fabStudentExam:FloatingActionButton by lazy { findViewById(R.id.fab_student_exam) }
    private val rvStudentExam:RecyclerView by lazy { findViewById(R.id.rv_student_exam) }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_exam)

        initClickListener()

        firestore = FirebaseFirestore.getInstance()
        initStudentExams()
        bindStudentExams()
    }

    private fun initClickListener(){
        fabStudentExam.setOnClickListener {
            startActivity(Intent(this,AddNewStudentExam::class.java))
        }
    }

    private fun initStudentExams() {
        rvStudentExam.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindStudentExams() {
        firestore?.collection(resources.getString(R.string.collection_name))?.addSnapshotListener { value, error ->
                value?.toObjects(StudentExam::class.java).let {
                    rvStudentExam.adapter = StudentExamAdapter(it as ArrayList<StudentExam>)
                }
            }
    }
}