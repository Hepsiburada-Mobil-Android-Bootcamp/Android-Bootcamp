package com.android.odevler.serkanozdemir.flights

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.FragmentNickNameLoginBinding
import com.android.camp.databinding.ItemFlightBinding
import com.android.odevler.serkanozdemir.data.Flight
import java.util.zip.Inflater

class FlightAdapter(private val context :Context, private val list : ArrayList<Flight>) : RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {
    private var _binding: ItemFlightBinding? = null
    private val binding get() = _binding!!
    inner class FlightViewHolder (view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flight,parent,false)
        _binding = DataBindingUtil.bind(view)
        return FlightViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        binding.flightNumber.text = list[position].flightNumber
        binding.from.text = list[position].from
        binding.to.text = list[position].to
        binding.departureTime.text = list[position].departureTime.toString()
        binding.landingTime.text = list[position].landingTime.toString()
        holder.itemView.setOnClickListener{
           Toast.makeText(context,list[position].flightStatus(),Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount()=list.size
}