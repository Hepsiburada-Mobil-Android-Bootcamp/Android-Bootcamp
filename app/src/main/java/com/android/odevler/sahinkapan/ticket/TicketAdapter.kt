package com.android.odevler.sahinkapan.ticket

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.android.camp.R
import com.android.camp.databinding.ItemTicketBinding


class TicketAdapter( context: Context, val travelList: ArrayList<Ticket>) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {


    class TicketViewHolder(private val binding:ItemTicketBinding) : RecyclerView.ViewHolder(binding.root){

       fun bind(ticket:Ticket){
           binding.ticket=ticket
       }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {

        return TicketViewHolder(ItemTicketBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        /*holder.textId.text = travelList[position].travelID
        holder.textVehicle.text = travelList[position].vehicleType
        holder.textFrom.text = travelList[position].fromCity
        holder.textTo.text = travelList[position].toCity
        holder.textPrice.text = travelList[position].price*/

        holder.bind(travelList[position])
    }

    override fun getItemCount(): Int {
        Log.e("HATA",travelList.toString())
        return travelList.size
    }


}