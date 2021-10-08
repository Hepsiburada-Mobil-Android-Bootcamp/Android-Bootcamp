package com.android.odevler.talhadengiz.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.ActivityStudentExamBinding
import com.android.odevler.talhadengiz.data.model.StudentExam
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class StudentExamActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null

    private var binding: ActivityStudentExamBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_student_exam)

        initClickListener()

        firestore = FirebaseFirestore.getInstance()
        initStudentExams()
        bindStudentExams()
    }

    private fun initClickListener() {
        binding?.fabStudentExam?.setOnClickListener {
            startActivity(Intent(this, AddNewStudentExamActivity::class.java))
        }
    }

    private fun initStudentExams() {
        binding?.rvStudentExam?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindStudentExams() {
        firestore?.collection(resources.getString(R.string.collection_name))
            ?.addSnapshotListener { value, error ->
                value?.toObjects(StudentExam::class.java).let {
                    binding?.rvStudentExam?.adapter = StudentExamAdapter(it as ArrayList<StudentExam>)
                }
            }
    }
}