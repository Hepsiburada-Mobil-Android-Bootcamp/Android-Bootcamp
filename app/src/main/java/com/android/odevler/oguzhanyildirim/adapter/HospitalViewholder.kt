package com.android.odevler.oguzhanyildirim.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.oguzhanyildirim.data.model.Hospital

class HospitalViewholder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvHospitalName: TextView by lazy { view.findViewById(R.id.tvHospitalName) }
    private val tvHospitalAddress: TextView by lazy { view.findViewById(R.id.tvHospitalAddress) }
    private val tvHospitalContact: TextView by lazy { view.findViewById(R.id.tvHospitalContact) }
    private val tvHospitalCapacity: TextView by lazy { view.findViewById(R.id.tvHospitalCapacity) }

    fun bind(hospital: Hospital) {
        tvHospitalName.text = hospital.name
        tvHospitalAddress.text = hospital.address
        tvHospitalContact.text = hospital.contactNumber
        tvHospitalCapacity.text = hospital.capacity

    }
}