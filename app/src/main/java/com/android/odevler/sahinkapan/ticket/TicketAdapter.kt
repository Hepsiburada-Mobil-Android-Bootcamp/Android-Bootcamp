package com.android.odevler.sahinkapan.ticket

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R


class TicketAdapter( context: Context, val travelList: ArrayList<Ticket>) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {



    inner class TicketViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textId = view.findViewById<TextView>(R.id.textId)
        val textVehicle= view.findViewById<TextView>(R.id.textVehicle)
        val textFrom= view.findViewById<TextView>(R.id.textFrom)
        val textTo= view.findViewById<TextView>(R.id.textTo)
        val textPrice= view.findViewById<TextView>(R.id.textPrice)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {

        return TicketViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ticket_item,parent,false))
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.textId.text = travelList[position].travelID
        holder.textVehicle.text = travelList[position].vehicleType
        holder.textFrom.text = travelList[position].fromCity
        holder.textTo.text = travelList[position].toCity
        holder.textPrice.text = travelList[position].price
    }

    override fun getItemCount(): Int {
        Log.e("HATA",travelList.toString())
        return travelList.size
    }


}