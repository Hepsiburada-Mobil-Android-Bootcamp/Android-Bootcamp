package com.android.odevler.damlaAlpan.car

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.damlaAlpan.data.Car

class CarViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val textViewCarBrand: TextView by lazy { view.findViewById(R.id.txtBrand) }
    private val textViewCarModel: TextView by lazy { view.findViewById(R.id.txtModel) }
    private val textViewCarFuelType: TextView by lazy { view.findViewById(R.id.txtFuelType) }


    fun bind(car: Car){
        textViewCarBrand.text = car.brand
        textViewCarModel.text = car.model
        textViewCarFuelType.text = car.fuelType

    }
}