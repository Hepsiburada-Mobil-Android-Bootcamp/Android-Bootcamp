package com.android.odevler.mehmetalivargun.adapter
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.databinding.ItemVehicleBinding
import com.android.odevler.mehmetalivargun.data.Car

class CarViewHolder(private val binding: ItemVehicleBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(car:Car){
        binding.car=car
    }
}