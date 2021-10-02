package com.android.odevler.serkanozdemir.flights

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.serkanozdemir.data.Flight

class FlightAdapter(private val context :Context, private val list : ArrayList<Flight>) : RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {

    inner class FlightViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val flightNumber by lazy { view.findViewById<TextView>(R.id.flightNumber) }
        val from by lazy { view.findViewById<TextView>(R.id.from) }
        val to by lazy { view.findViewById<TextView>(R.id.to) }
        val departureTime by lazy { view.findViewById<TextView>(R.id.departureTime) }
        val landingTime by lazy { view.findViewById<TextView>(R.id.landingTime) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder
        =FlightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_flight,parent,false))

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.flightNumber.text = list[position].flightNumber
        holder.from.text = list[position].from
        holder.to.text = list[position].to
        holder.departureTime.text = list[position].departureTime.toString()
        holder.landingTime.text = list[position].landingTime.toString()
        holder.itemView.setOnClickListener{
           Toast.makeText(context,list[position].flightStatus(),Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount()=list.size
}