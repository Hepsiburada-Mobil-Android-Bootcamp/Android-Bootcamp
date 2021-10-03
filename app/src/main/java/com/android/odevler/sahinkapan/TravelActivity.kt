package com.android.odevler.sahinkapan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R

import com.android.camp.data.model.Exam
import com.android.camp.exam.ExamAdapter
import com.android.odevler.sahinkapan.ticket.Ticket
import com.android.odevler.sahinkapan.ticket.TicketAdapter
import com.google.firebase.firestore.FirebaseFirestore

class TravelActivity : AppCompatActivity() {
    val addTravelFab by lazy { findViewById<View>(R.id.addTravel) }
    val ticketRecyclerView by lazy { findViewById<RecyclerView>(R.id.ticketRecycler) }
    var firestore:FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)





        firestore = FirebaseFirestore.getInstance()

        val layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        ticketRecyclerView.layoutManager=layoutManager


        bindTickets()

        addTravelFab.setOnClickListener {
            val intent = Intent(this,AddTravelActivity::class.java)
            startActivity(intent)
        }


    }



    private fun bindTickets() {
        firestore?.collection("sahinkapan")?.orderBy("travelID")?.addSnapshotListener { value, error ->
            val travelList = arrayListOf<Ticket>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Ticket::class.java).also { ticket ->

                    travelList.add(ticket)
                }
            }

            ticketRecyclerView.adapter = TicketAdapter(this, travelList)
        }


    }
}