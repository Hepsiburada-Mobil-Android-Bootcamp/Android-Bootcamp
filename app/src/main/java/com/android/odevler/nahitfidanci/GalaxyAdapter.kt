package com.android.odevler.nahitfidanci

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.nahitfidanci.data.Galaxy

class GalaxyAdapter(var galaxies: ArrayList<Galaxy>) :
    RecyclerView.Adapter<GalaxyAdapter.GalaxyViewHolder>() {
    inner class GalaxyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name by lazy { itemView.findViewById<TextView>(R.id.galaxyName) }
        private val year by lazy { itemView.findViewById<TextView>(R.id.galaxyExploredYear) }
        private val distance by lazy { itemView.findViewById<TextView>(R.id.galaxyLightYearDistance) }
        private val stars by lazy { itemView.findViewById<TextView>(R.id.galaxyKnownNumOfStars) }


        fun bind(galaxy: Galaxy) {
            name.text = galaxy.name
            year.text = galaxy.exploredYear.toString()
            distance.text = galaxy.lightYearDistance.toString()
            stars.text = galaxy.knownNumOfStars.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalaxyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_galaxy, parent, false)
        return GalaxyViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalaxyViewHolder, position: Int) {
        holder.bind(galaxies[position])
    }

    override fun getItemCount(): Int {
        return galaxies.size
    }
}