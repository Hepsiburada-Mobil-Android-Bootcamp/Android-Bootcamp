package com.android.odevler.abdulwahid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R

class SongListActivity : AppCompatActivity() {

    private val recyclerView by lazy {findViewById<RecyclerView>(R.id.rv_song)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
    }
}