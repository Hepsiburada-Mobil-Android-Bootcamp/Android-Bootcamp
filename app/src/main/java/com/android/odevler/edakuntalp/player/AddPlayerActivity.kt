package com.android.odevler.edakuntalp.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.jar.Manifest

class AddPlayerActivity : AppCompatActivity() {

    val editName by lazy { findViewById<EditText>(R.id.editTextName) }
    val editSurname by lazy { findViewById<EditText>(R.id.editTextSurname) }
    val editBornYear by lazy { findViewById<EditText>(R.id.editTextBornYear) }
    val editConsoleType by lazy { findViewById<EditText>(R.id.editTextConsoleType)}

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_player)
        firestore= Firebase.firestore

    }

    fun saveOnClick(view:View) {

        val name = editName.text.toString()
        val surname = editSurname.text.toString()
        val bornYear = editBornYear.text.toString()
        val consoleType = editConsoleType.text.toString()



        if (!(name.isNullOrBlank() || surname.isNullOrBlank() || bornYear.isNullOrBlank() || consoleType.isNullOrBlank())) {



            val hashmap =
                hashMapOf<String, Any>()
            hashmap.put("name", name)
            hashmap.put("surname", surname)
            hashmap.put("bornYear", bornYear)
            hashmap.put("consoleType", consoleType)

            firestore.collection("edakuntalp").add(hashmap).addOnSuccessListener {
                Toast.makeText(
                    this@AddPlayerActivity,
                    "Player uploaded successful!!",
                    Toast.LENGTH_LONG
                ).show()
                finish()

            }.addOnFailureListener {
                Toast.makeText(this@AddPlayerActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }else{
            Toast.makeText(this,"Fill the blank spaces",Toast.LENGTH_LONG).show()
        }


    }
}
