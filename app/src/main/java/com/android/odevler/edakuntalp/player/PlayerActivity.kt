package com.android.odevler.edakuntalp.player

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.edakuntalp.data.model.Player
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class PlayerActivity : AppCompatActivity() {

    val recyclerViewPlayer by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private lateinit var firestore: FirebaseFirestore
    val players: ArrayList<Player> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        firestore = Firebase.firestore
        downloadData()
    }

    fun fabOnClick(view: View) {
        val intent = Intent(this, AddPlayerActivity::class.java)
        startActivity(intent)
    }

    private fun downloadData() {
        firestore.collection("edakuntalp").addSnapshotListener { value, error ->

            if (error != null) {
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {

                        players.clear()

                        val documents = value.documents

                        for (document in documents) {

                            val name = document.get("name") as? String
                            val surname = document.get("surname") as? String
                            val bornYear = document.get("bornYear") as? String
                            val consoleType = document.get("consoleType") as? String

                            val player = Player(name, surname, bornYear?.toInt(), consoleType)
                            players.add(player)
                        }
                        initAdapter(players)
                    } else {
                        Toast.makeText(this, "Empty value!", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }

    private fun initAdapter(players: ArrayList<Player>) {

        recyclerViewPlayer.layoutManager = LinearLayoutManager(this)
        val adapter = PlayerRecyclerAdapter(players)
        recyclerViewPlayer.adapter = adapter

    }
}

