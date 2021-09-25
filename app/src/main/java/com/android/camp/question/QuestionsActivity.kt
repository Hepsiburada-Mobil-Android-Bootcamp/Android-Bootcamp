package com.android.camp.question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.CampHelper
import com.android.camp.data.model.Question
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class QuestionsActivity : AppCompatActivity() {

    private var firestore:FirebaseFirestore? = null

    private val fab by lazy { findViewById<View>(R.id.fab) }
    private val recyclerViewQuestion by lazy { findViewById<RecyclerView>(R.id.recycler_view_question) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        fab.setOnClickListener {
            startActivity(Intent(this, AddNewQuestionActivity::class.java))
        }

        Log.d("QuestionsActivity", "onCreate")

        initQuestions()

        firestore = FirebaseFirestore.getInstance()
        bindQuestions()
    }

    private fun initQuestions() {
        recyclerViewQuestion.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindQuestions() {
        firestore?.collection("questions")?.addSnapshotListener { value, error ->
            value?.toObjects(Question::class.java).let { questions ->
                recyclerViewQuestion.adapter = QuestionAdapter(questions as ArrayList<Question>)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("QuestionsActivity", "onResume")
        Log.d("QuestionsActivity", "list: ${CampHelper.list.size} ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("QuestionsActivity", "onStart")

    }

    override fun onPause() {
        super.onPause()
        Log.d("QuestionsActivity", "onPause")

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("QuestionsActivity", "onDestroy")

    }

    override fun onStop() {
        super.onStop()
        Log.d("QuestionsActivity", "onStop")

    }
}