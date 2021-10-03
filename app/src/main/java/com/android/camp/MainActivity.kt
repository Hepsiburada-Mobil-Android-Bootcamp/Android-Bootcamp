package com.android.camp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.data.model.Exam
import com.android.camp.exam.AddNewExamActivity
import com.android.camp.exam.ExamAdapter
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    val addNewExamFab by lazy { findViewById<View>(R.id.fab) }
    val recyclerViewExam by lazy { findViewById<RecyclerView>(R.id.recycler_view_exam) }

    var firestore:FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNewExamFab.setOnClickListener {
            val intent = Intent(this, AddNewExamActivity::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()

        initExams()
        bindExams()
    }

    private fun initExams() {
        recyclerViewExam.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindExams() {
        firestore?.collection("exam")?.orderBy("date")?.addSnapshotListener { value, error ->
            val list = arrayListOf<Exam>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Exam::class.java).also {  exam ->
                    exam.id = queryDocumentSnapshot.id
                    list.add(exam)
                }
            }

            recyclerViewExam.adapter = ExamAdapter(this, list)
        }
    }
}