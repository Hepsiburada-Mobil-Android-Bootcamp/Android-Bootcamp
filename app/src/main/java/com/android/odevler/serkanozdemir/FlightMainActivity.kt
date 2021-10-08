package com.android.odevler.serkanozdemir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.ActivityAddNewFlightBinding
import com.android.camp.databinding.ActivityFlightMainBinding
import com.android.odevler.serkanozdemir.data.Flight
import com.android.odevler.serkanozdemir.flights.AddNewFlight
import com.android.odevler.serkanozdemir.flights.FlightAdapter
import com.google.firebase.firestore.FirebaseFirestore

class FlightMainActivity : AppCompatActivity() {

    private var bindingFlight: ActivityFlightMainBinding?=null

    var firestore: FirebaseFirestore? = null

    val flightList = arrayListOf<Flight>(
        Flight("TK123213","İstanbul","İzmir","10.10","12.20"),
        Flight("TK145","İstanbul","Üsküp","10.10","12.20"),
        Flight("TK12455","İstanbul","Paris","10.10","12.20"),
        Flight("TK12656","İstanbul","Ankara","10.10","12.20"),
        Flight("TK1276713","İstanbul","Berlin","10.10","12.20"),
        Flight("TK1276713","İstanbul","Berlin","10.10","12.20"),
        Flight("TK1276713","İstanbul","Berlin","10.10","12.20"),
        Flight("TK1276713","İstanbul","Berlin","10.10","12.20")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_main)
        bindingFlight = DataBindingUtil.setContentView(this,R.layout.activity_flight_main)
        bindingFlight?.addFlight?.setOnClickListener {
            val intent = Intent(this,AddNewFlight::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()
        initFlights()
        bindFlights()

    }

    private fun initFlights(){
        bindingFlight?.flights?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    private fun bindFlights(){
        firestore?.collection("serkanozdemir")?.addSnapshotListener { value, error ->
            val list = arrayListOf<Flight>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Flight::class.java).also { flight ->
                    list.add(flight)
                }
            }

            bindingFlight?.flights?.adapter = FlightAdapter(this, list)
        }
        //recyclerView.adapter = FlightAdapter(this, flightList)
    }
}