package com.android.odevler.abdulwahid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.abdulwahid.adapter.SongAdapter
import com.android.odevler.abdulwahid.data.Song
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class SongListActivity : AppCompatActivity() {

    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.rv_song) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        initData()
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initData() {
        fireStore.collection("abdulwahid").addSnapshotListener { value, error ->
            value?.toObjects(Song::class.java).let {
                recyclerView.adapter = SongAdapter(it as ArrayList<Song>)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.song_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add_song){
            startActivity(Intent(this, AddNewSongActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}