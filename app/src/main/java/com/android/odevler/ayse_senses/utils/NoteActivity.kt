package com.android.odevler.ayse_senses.utils

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.firestore.FirestoreHelper
import com.android.odevler.ayse_senses.data.model.Note
import com.android.odevler.ayse_senses.note.AddNoteActivity
import com.android.odevler.ayse_senses.note.NoteAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NoteActivity : AppCompatActivity() {

    private val fab by lazy { findViewById<View>(R.id.fab) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayse_home_note_activity)

        initNote()

        fab.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        bindNote()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.ayse_menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.last_note -> {
                lastNotes()
                true
            }
            R.id.ordery_priority -> {
                bindNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initNote() {
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun lastNotes() {
        recyclerView.adapter = NoteAdapter(FirestoreHelper().getNoteQueryOrderByDate())

    }

    private fun bindNote() {
        recyclerView.adapter = NoteAdapter(FirestoreHelper().getNoteQueryOrderByPriority())
    }
}