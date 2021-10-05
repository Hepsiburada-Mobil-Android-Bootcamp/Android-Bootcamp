package com.android.odevler.ozantakir.NbaChamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddNbaChampActivity : AppCompatActivity() {
    val editTeamName by lazy { findViewById<EditText>(R.id.edit_team_name) }
    val editYear by lazy { findViewById<EditText>(R.id.edit_year) }
    val editMvp by lazy { findViewById<EditText>(R.id.edit_mvp) }
    val editGamesPlayed by lazy { findViewById<EditText>(R.id.edit_games_played) }

    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_nba_champ)

        firestore= Firebase.firestore
    }

    fun save(view: View) {
        val teamName = editTeamName.text.toString()
        val championshipYear = editYear.text.toString()
        val finalsMvp = editMvp.text.toString()
        val gamesPlayedInFinals = editGamesPlayed.text.toString()


        if (teamName.isNullOrEmpty() || championshipYear.isNullOrEmpty() || finalsMvp.isNullOrEmpty()
            || gamesPlayedInFinals.isNullOrEmpty()) {
            Toast.makeText(this,"Fill in the blanks", Toast.LENGTH_LONG).show()
        } else {
            val hashmap =
                hashMapOf<String, Any>()
            hashmap.put("teamName", teamName)
            hashmap.put("championshipYear", championshipYear)
            hashmap.put("finalsMvp", finalsMvp)
            hashmap.put("gamesPlayedInFinals", gamesPlayedInFinals)

            firestore.collection("ozantakir").add(hashmap).addOnSuccessListener {
                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
                startActivity(
                    Intent(
                        this,
                        NbaChampsListActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                )
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}