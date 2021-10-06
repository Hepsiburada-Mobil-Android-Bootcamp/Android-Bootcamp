package com.android.odevler.sahinkapan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R

import com.android.camp.data.model.Exam
import com.android.camp.databinding.ActivityTravelBinding
import com.android.camp.exam.ExamAdapter
import com.android.odevler.sahinkapan.ticket.Ticket
import com.android.odevler.sahinkapan.ticket.TicketAdapter
import com.google.firebase.firestore.FirebaseFirestore

class TravelActivity : AppCompatActivity() {



    var firestore:FirebaseFirestore? = null

    var bindingTravel:ActivityTravelBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bindingTravel = DataBindingUtil.setContentView(this,R.layout.activity_travel)




        firestore = FirebaseFirestore.getInstance()

        val layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        bindingTravel?.ticketRecycler?.layoutManager=layoutManager


        bindTickets()

        bindingTravel?.addTravel?.setOnClickListener {
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

            bindingTravel?.ticketRecycler?.adapter = TicketAdapter(this, travelList)
        }


    }
}