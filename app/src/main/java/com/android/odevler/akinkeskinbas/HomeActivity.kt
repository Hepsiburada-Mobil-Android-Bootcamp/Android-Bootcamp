package com.android.odevler.akinkeskinbas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.akinkeskinbas.data.model.Users
import com.android.odevler.akinkeskinbas.rehber.AddUserActivity
import com.android.odevler.akinkeskinbas.rehber.RehberAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.yasincetin.firebasesdk.firestore.FirestoreAdapter

class HomeActivity : AppCompatActivity() {

    val addNewUser by lazy { findViewById<View>(R.id.fab) }
    private val rcRehber: RecyclerView by lazy { findViewById(R.id.rc_rehber) }
    private var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        firestore = FirebaseFirestore.getInstance()

        //rc logic
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcRehber.layoutManager = layoutManager
        rcRehber.setHasFixedSize(true)

        //func
        getAllUsers()
        //deleteAllUsers()


        addNewUser.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }


    }

    private fun getAllUsers() {
        val query = firestore?.collection("akinkeskinbas")?.orderBy("date")
        rcRehber.adapter = RehberAdapter(query)
    }

    private fun deleteAllUsers() {
        firestore?.collection("akinkeskinbas")?.document("LsIIzzMfxe0mvOrIoFvH")?.delete()
            ?.addOnSuccessListener {
                println("basarili Sekilde Silindi")
            }?.addOnFailureListener {
            println(it.toString())
        }
    }

}