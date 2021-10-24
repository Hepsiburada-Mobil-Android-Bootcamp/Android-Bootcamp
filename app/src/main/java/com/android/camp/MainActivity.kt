package com.android.camp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.data.model.Exam
import com.android.camp.exam.AddNewExamActivity
import com.android.camp.exam.ExamAdapter
import com.android.camp.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    val addNewExamFab by lazy { findViewById<View>(R.id.fab) }
    val recyclerViewExam by lazy { findViewById<RecyclerView>(R.id.recycler_view_exam) }

    var firestore: FirebaseFirestore? = null
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNewExamFab.setOnClickListener {
            val intent = Intent(this, AddNewExamActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.button_sign_out).setOnClickListener {
            auth.signOut()
            isLogined()
        }

        firestore = FirebaseFirestore.getInstance()
    }

    override fun onStart() {
        super.onStart()
        isLogined()
    }

    private fun isLogined() {
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }



    }

    override fun onResume() {
        super.onResume()

        Toast.makeText(
            this,
            getString(R.string.welcome_user, auth.currentUser?.uid),
            Toast.LENGTH_LONG
        ).show()

        initExams()
        bindExams()
    }

    private fun initExams() {
        recyclerViewExam.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindExams() {
        /*
        firestore?.collection("exam")?.orderBy("date")?.addSnapshotListener { value, error ->
            val list = arrayListOf<Exam>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Exam::class.java).also { exam ->
                    exam.id = queryDocumentSnapshot.id
                    list.add(exam)
                }
            }

            recyclerViewExam.adapter = ExamAdapter(this, list)
        }
        */


        val queryExam = firestore?.collection("exam")?.orderBy("date")
        val adapter = ExamAdapter(this, queryExam)
        recyclerViewExam.adapter = adapter
    }
}