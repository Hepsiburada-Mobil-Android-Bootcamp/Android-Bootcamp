package com.android.camp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.data.model.Exam
import com.android.camp.databinding.ActivityMainBinding
import com.android.camp.exam.AddNewExamActivity
import com.android.camp.exam.ExamAdapter
import com.android.camp.question.QuestionsActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    var firestore:FirebaseFirestore? = null
    var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.fab?.setOnClickListener {
            val intent = Intent(this, AddNewExamActivity::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()

        bindExams()
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

            binding?.model = MainViewModel().apply {
                exams.addAll(list)
            }
        }
    }
}