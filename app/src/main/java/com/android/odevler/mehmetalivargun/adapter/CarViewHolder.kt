package com.android.odevler.mehmetalivargun.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.mehmetalivargun.data.Car

class CarViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val layout by lazy { itemView.findViewById<View>(R.id.layout_root_car) }
    private val textViewBrand by lazy { itemView.findViewById<TextView>(R.id.brand_name) }
    private val textViewModel by lazy { itemView.findViewById<TextView>(R.id.car_model) }
    private val textViewModelYear by lazy { itemView.findViewById<TextView>(R.id.model_year) }
    private val textViewMaxSpeed by lazy { itemView.findViewById<TextView>(R.id.max_speed) }

    fun bind(car:Car){
        textViewBrand.text=car.brand
        textViewModel.text=car.model
        textViewModelYear.text="${car.modelYear}"
        textViewMaxSpeed.text="${car.maxSpeed}"
    }
}