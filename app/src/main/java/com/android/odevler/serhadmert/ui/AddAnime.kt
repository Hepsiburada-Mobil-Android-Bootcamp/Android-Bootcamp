package com.android.odevler.serhadmert.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.serhadmert.data.Anime
import com.google.firebase.firestore.FirebaseFirestore

class AddAnime : AppCompatActivity() {

    val name by lazy { findViewById<EditText>(R.id.anime_name) }
    val type by lazy { findViewById<EditText>(R.id.type) }
    val releaseDate by lazy { findViewById<EditText>(R.id.release_date) }
    val malPoint by lazy { findViewById<EditText>(R.id.mal_point) }
    val add by lazy { findViewById<View>(R.id.add_anime) }

    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_anime)

        add.setOnClickListener {
            if (isValid()) {
                save()
            }
        }

        firestore = FirebaseFirestore.getInstance()
    }


    private fun save(){

        val anime=Anime(
            name=name.text.toString(),
            type=type.text.toString(),
            malPoint = malPoint.text.toString().toDouble(),
            releaseDate = releaseDate.text.toString()
        )
        firestore?.collection("serhadmert")?.add(anime)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Anime Eklenemedi...", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun EditText.isValid(): Boolean {
        if (text.isNullOrEmpty()) {
            requestFocus()
            error = "bu alan gereklii.."
        }

        return text.isNotEmpty()
    }

    private fun isValid(): Boolean {
        var isValid = true

        arrayListOf(
            name,
            type,
            releaseDate,
            malPoint,
        ).forEach { editText ->
            isValid = editText.isValid() && isValid
        }

        return isValid
    }
}