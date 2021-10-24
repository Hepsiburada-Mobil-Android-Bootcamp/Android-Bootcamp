package com.android.odevler.burakisik.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.burakisik.adapter.AttenderAdapter
import com.android.odevler.burakisik.data.model.Attender
import com.android.odevler.burakisik.dialog.NewAttenderAdderDialog
import com.google.firebase.firestore.FirebaseFirestore

class AttendersActivity : AppCompatActivity() {

    private var fireStore: FirebaseFirestore? = null
    private val fab by lazy { findViewById<View>(R.id.fab) }
    private val rvAttenders by lazy { findViewById<RecyclerView>(R.id.rvAttender) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.burakisik_attender_list)

        fab.setOnClickListener {
            val dialog = NewAttenderAdderDialog()
            dialog.show(this.supportFragmentManager.beginTransaction(), NewAttenderAdderDialog::class.java.name)
        }

        populateRvAttender()
        fireStore = FirebaseFirestore.getInstance()
        bindAttenders()
    }

    private fun populateRvAttender() {
        rvAttenders.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

     fun bindAttenders() {
        fireStore?.collection("event")?.document("Kotlin Bootcamp")?.collection("attenders")
            ?.addSnapshotListener { value, error ->
                value?.toObjects(Attender::class.java).let { attenders ->
                    rvAttenders.adapter = AttenderAdapter(attenders as ArrayList<Attender>)
                }
            }
    }
}