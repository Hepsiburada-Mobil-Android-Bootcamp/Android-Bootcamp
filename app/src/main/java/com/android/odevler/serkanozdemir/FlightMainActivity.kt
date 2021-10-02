package com.android.odevler.serkanozdemir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.serkanozdemir.data.Flight
import com.android.odevler.serkanozdemir.flights.AddNewFlight
import com.android.odevler.serkanozdemir.flights.FlightAdapter
import com.google.firebase.firestore.FirebaseFirestore

class FlightMainActivity : AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.flights) }
    private val addFlightButton by lazy { findViewById<Button>(R.id.addFlight) }

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
        addFlightButton.setOnClickListener {
            val intent = Intent(this,AddNewFlight::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()
        initFlights()
        bindFlights()

    }

    private fun initFlights(){
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
    private fun bindFlights(){
        firestore?.collection("serkanozdemir")?.addSnapshotListener { value, error ->
            val list = arrayListOf<Flight>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Flight::class.java).also { flight ->
                    list.add(flight)
                }
            }

            recyclerView.adapter = FlightAdapter(this, list)
        }
        //recyclerView.adapter = FlightAdapter(this, flightList)
    }
}