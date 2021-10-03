package com.android.odevler.cagrikilic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.camp.R
import com.android.odevler.cagrikilic.data.Paper
import com.google.firebase.firestore.FirebaseFirestore
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.question.AddNewQuestionActivity

class PaperListActivity : AppCompatActivity() {

    private var firestore : FirebaseFirestore?=null
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view_paper) }
    private val fab by lazy { findViewById<View>(R.id.fab) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_list)
        firestore = FirebaseFirestore.getInstance()
        fab.setOnClickListener {
            startActivity(Intent(this, AddPaperActivity::class.java))
        }
        getPaperList()
    }
    private fun getPaperList() {
        firestore?.collection("cagrikilic")?.get()?.addOnSuccessListener {
            snapshot ->
                snapshot.toObjects(Paper::class.java)?.let { papers ->
                recyclerView.adapter = PaperListAdapter(papers as ArrayList<Paper>)
            }
        }
    }
}