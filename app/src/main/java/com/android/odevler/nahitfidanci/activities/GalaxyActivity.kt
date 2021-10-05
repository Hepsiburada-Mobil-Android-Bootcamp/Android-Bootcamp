package com.android.odevler.nahitfidanci.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.nahitfidanci.GalaxyAdapter
import com.android.odevler.nahitfidanci.GalaxyHelper
import com.android.odevler.nahitfidanci.data.Galaxy
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class GalaxyActivity : AppCompatActivity() {
    private val addNewGalaxy by lazy { findViewById<FloatingActionButton>(R.id.addNewGalaxy) }
    private val galaxyRecyclerView by lazy { findViewById<RecyclerView>(R.id.rv_galaxy) }
    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.galaxy_rc_view)

        firestore = FirebaseFirestore.getInstance()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        galaxyRecyclerView.layoutManager = layoutManager


        bindGalaxies()

        addNewGalaxy.setOnClickListener {
            val intent = Intent(this, AddNewGalaxyActivity::class.java)
            startActivity(intent)
        }


    }

    private fun bindGalaxies() {
        firestore?.collection("nahitfidanci")?.orderBy("name")
            ?.addSnapshotListener { value, error ->
                val galaxyList = arrayListOf<Galaxy>()

                value?.forEach {
                    it.toObject(Galaxy::class.java).also { galaxy -> galaxyList.add(galaxy) }
                }

                galaxyRecyclerView.adapter = GalaxyAdapter(galaxyList)

            }
    }
}