package com.android.odevler.mehmetalivargun.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.mehmetalivargun.data.Car

class CarAdapter(private val context:Context,private val cars:ArrayList<Car>):RecyclerView.Adapter<CarViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
      return CarViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle,parent,false))
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
       val car= cars[position]
        holder.bind(car)

    }

    override fun getItemCount()=cars.size

}