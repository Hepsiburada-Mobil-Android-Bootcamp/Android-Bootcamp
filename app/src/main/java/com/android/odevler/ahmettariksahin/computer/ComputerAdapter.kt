package com.android.odevler.ahmettariksahin.computer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.ahmettariksahin.model.Computer

class ComputerAdapter(private val list: ArrayList<Computer>): RecyclerView.Adapter<ComputerAdapter.ComputerViewHolder>(){
    inner class ComputerViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val powerSupply: TextView by lazy { view.findViewById<TextView>(R.id.power) }
        val screenModel: TextView by lazy { view.findViewById<TextView>(R.id.screen) }
        val motherBoard: TextView by lazy { view.findViewById<TextView>(R.id.motherBoard) }
        val ram: TextView by lazy { view.findViewById<TextView>(R.id.ram) }
        val keyboard: TextView by lazy { view.findViewById<TextView>(R.id.keyboard) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComputerViewHolder
            =ComputerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_computer,parent,false))

    override fun onBindViewHolder(holder: ComputerViewHolder, position: Int) {
        holder.powerSupply.text = list[position].powerSupply
        holder.screenModel.text = list[position].screenModel
        holder.motherBoard.text = list[position].motherBoard
        holder.ram.text = list[position].ram
        holder.keyboard.text = list[position].keyboard

    }

    override fun getItemCount()=list.size

}
