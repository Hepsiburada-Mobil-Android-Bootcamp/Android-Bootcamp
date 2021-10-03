package com.android.odevler.serhadmert.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.serhadmert.adapter.AnimeAdapter
import com.android.odevler.serhadmert.data.Anime
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    val add by lazy { findViewById<View>(R.id.fab_add) }
    val recyclerViewAnime by lazy { findViewById<RecyclerView>(R.id.recycler_view_anime) }
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        firestore = FirebaseFirestore.getInstance()
        getAnimes()

        add.setOnClickListener {
            startActivity(Intent(this, AddAnime::class.java))
        }
    }

    private fun getAnimes(){

        firestore?.collection("serhadmert")?.addSnapshotListener { value, error ->
            val list = arrayListOf<Anime>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Anime::class.java).also { anime ->
                    list.add(anime)
                }
            }
            recyclerViewAnime.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerViewAnime.adapter = AnimeAdapter(this,list)
         }
    }
}