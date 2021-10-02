package com.android.odevler.abdulwahid.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.abdulwahid.adapter.SongAdapter
import com.android.odevler.abdulwahid.data.Song
import com.google.firebase.firestore.FirebaseFirestore


class SongListActivity : AppCompatActivity() {

    private val fireStore by lazy { FirebaseFirestore.getInstance() }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.rv_song) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        initRecyclerView()
        initData()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initData() {
        fireStore.collection("abdulwahid").addSnapshotListener { value, _ ->
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
        if (item.itemId == R.id.menu_add_song) {
            startActivity(Intent(this, AddNewSongActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}