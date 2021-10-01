package com.android.odevler.cagrikilic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.camp.R
import com.android.odevler.cagrikilic.data.Paper
import com.google.firebase.firestore.FirebaseFirestore

class PaperListActivity : AppCompatActivity() {

    private var firestore : FirebaseFirestore?=null
    private val recyclerView by lazy { findViewById<View>(R.id.recycler_view_paper) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paper_list)
        firestore = FirebaseFirestore.getInstance()

    }
    private fun getPaperList() {
        firestore?.collection("cagrikilic")?.get()?.addOnSuccessListener {
            snapshot ->
            snapshot.toObjects(Paper::class.java)?.let { papers ->
                recyclerView.ada
            }
        }
    }
}