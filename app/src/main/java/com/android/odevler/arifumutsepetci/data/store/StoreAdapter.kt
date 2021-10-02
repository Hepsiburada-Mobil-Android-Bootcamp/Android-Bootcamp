package com.android.odevler.arifumutsepetci.data.store
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.arifumutsepetci.data.model.Store

class StoreAdapter(private var context: Context, private var list: ArrayList<Store>):
    RecyclerView.Adapter<StoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        )
    }
    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(list[position])
    }
    override fun getItemCount() = list.size
}