package com.android.odevler.oguzhanyildirim.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.ActivityHospitalListBinding
import com.android.odevler.oguzhanyildirim.adapter.HospitalAdapter
import com.android.odevler.oguzhanyildirim.data.model.Hospital
import com.google.firebase.firestore.FirebaseFirestore


class HospitalListActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null
    private var hospitalListBinding : ActivityHospitalListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hospitalListBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_hospital_list
        )

        firestore = FirebaseFirestore.getInstance()

        getHospitals()

        hospitalListBinding?.btnAdd?.setOnClickListener {
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

            hospitalListBinding?.recyclerViewHospital?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            hospitalListBinding?.recyclerViewHospital?.adapter = HospitalAdapter(hospitalList)
        }
    }
}