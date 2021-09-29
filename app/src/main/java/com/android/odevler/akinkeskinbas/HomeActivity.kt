package com.android.odevler.akinkeskinbas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Exam
import com.android.camp.exam.ExamAdapter
import com.android.odevler.akinkeskinbas.data.model.Users
import com.android.odevler.akinkeskinbas.rehber.AddUserActivity
import com.android.odevler.akinkeskinbas.rehber.RehberAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    val addNewUser by lazy { findViewById<View>(R.id.fab) }
    private val rcRehber: RecyclerView by lazy {findViewById(R.id.rc_rehber)}
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
    private  fun getAllUsers(){
        firestore?.collection("akinkeskinbas")?.orderBy("date")?.addSnapshotListener { value, error ->
            val list = arrayListOf<Users>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Users::class.java).also { exam ->

                    list.add(exam)
                }
            }

            rcRehber.adapter = RehberAdapter(this, list)
        }
    }
    private  fun deleteAllUsers(){
        firestore?.collection("akinkeskinbas")?.document("LsIIzzMfxe0mvOrIoFvH")?.delete()?.addOnSuccessListener {
            println("basarili Sekilde Silindi")
        }?.addOnFailureListener {
            println(it.toString())
        }
    }


}