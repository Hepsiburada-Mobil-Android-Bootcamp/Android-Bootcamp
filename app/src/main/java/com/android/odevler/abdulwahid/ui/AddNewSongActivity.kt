package com.android.odevler.abdulwahid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.abdulwahid.data.Song
import com.google.firebase.firestore.FirebaseFirestore

class AddNewSongActivity : AppCompatActivity() {

    private val tvSongName by lazy { findViewById<EditText>(R.id.tv_add_song_name) }
    private val tvArtistName by lazy { findViewById<EditText>(R.id.tv_add_artist_name) }
    private val tvDurationTime by lazy { findViewById<EditText>(R.id.tv_add_duration_time) }
    private val tvYear by lazy { findViewById<EditText>(R.id.tv_add_year) }
    private val btnSave by lazy { findViewById<View>(R.id.btn_save_song) }

    private val fireStore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_song)
    }

    override fun onResume() {
        super.onResume()
        btnSave.setOnClickListener {
            if (editTextNullCheck() && checkTime())
                saveSong()
        }
    }

    private fun saveSong() {
        val song = createSong()

        fireStore.collection("abdulwahid").add(song).addOnSuccessListener {
            Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun createSong(): Song {
        return Song(
            tvSongName.text.toString(),
            tvArtistName.text.toString(),
            tvDurationTime.text.toString().toDouble(),
            tvYear.text.toString().toInt()
        )
    }

    private fun checkTime(): Boolean {
        return if (tvYear.text.toString().toInt() > 2021){
            tvYear.error = "Welcome from future"
            false
        } else{
            true
        }
    }

    private fun editTextNullCheck(): Boolean {
        var isValid = true
        arrayListOf<EditText>(tvSongName, tvArtistName, tvDurationTime, tvYear).forEach {
            isValid = it.checkIsNullOrBlank() && isValid
        }
        return isValid
    }

    private fun EditText.checkIsNullOrBlank(): Boolean {
        return if (text.isNullOrBlank()) {
            error = "Please fill in the blank"
            false
        } else {
            true
        }
    }
}