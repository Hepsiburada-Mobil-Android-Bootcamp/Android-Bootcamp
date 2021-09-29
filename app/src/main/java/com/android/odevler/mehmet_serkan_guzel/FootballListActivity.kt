package com.android.odevler.mehmet_serkan_guzel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R

import com.android.odevler.mehmet_serkan_guzel.data.Football
import com.google.firebase.firestore.FirebaseFirestore


class FootballListActivity : AppCompatActivity() {
    private  val addNewFootball by lazy {
        findViewById<View>(R.id.fabFootball)
    }
    private val recyclerViewFootball by lazy {
        findViewById<RecyclerView>(R.id.recycler_view_football)
    }
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_list)
        addNewFootball.setOnClickListener {
            val intent = Intent(this, AddNewFootballActivity::class.java)
            startActivity(intent)
        }
        firestore = FirebaseFirestore.getInstance()

        initFootball()
        bindFootball()
    }

    private fun initFootball() {
        recyclerViewFootball.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindFootball() {
        firestore?.collection("MehmetSerkanGuzel")?.addSnapshotListener { value, _ ->
            val list = arrayListOf<Football>()
            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Football::class.java).also { football ->
                    list.add(football)
                }
            }
            recyclerViewFootball.adapter = FootballAdapter(list)
        }
    }
}