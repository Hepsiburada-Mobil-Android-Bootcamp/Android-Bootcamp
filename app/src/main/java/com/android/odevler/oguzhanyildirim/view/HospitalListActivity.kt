package com.android.odevler.oguzhanyildirim.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.oguzhanyildirim.adapter.HospitalAdapter
import com.android.odevler.oguzhanyildirim.data.model.Hospital
import com.google.firebase.firestore.FirebaseFirestore


class HospitalListActivity : AppCompatActivity() {

    private val recyclerViewHospital by lazy { findViewById<RecyclerView>(R.id.recyclerViewHospital) }
    private val btnAdd by lazy { findViewById<Button>(R.id.btnAdd) }
    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_list)
        firestore = FirebaseFirestore.getInstance()

        getHospitals()

        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddHospitalActivity::class.java))
        }

    }

    private fun getHospitals() {
        firestore?.collection("oguzhanyildirim")?.addSnapshotListener { value, error ->
            val hospitalList = arrayListOf<Hospital>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Hospital::class.java).also {  hospital ->
                    hospitalList.add(hospital)
                }
            }

            recyclerViewHospital.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerViewHospital.adapter = HospitalAdapter(hospitalList)
        }
    }
}