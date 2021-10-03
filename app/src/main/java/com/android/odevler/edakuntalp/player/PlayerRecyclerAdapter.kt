package com.android.odevler.edakuntalp.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.databinding.PlayerRecyclerviewRowBinding
import com.android.odevler.edakuntalp.data.model.Player

class PlayerRecyclerAdapter (val player:ArrayList<Player>):RecyclerView.Adapter<PlayerRecyclerAdapter.PlayerHolder>() {

    class PlayerHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameText by lazy { itemView.findViewById<TextView>(R.id.textViewNameOutput) }
        private val surnameText by lazy { itemView.findViewById<TextView>(R.id.textViewSurnameOutput) }
        private val bornYearText by lazy { itemView.findViewById<TextView>(R.id.textViewBornYearOutput) }
        private val consoleTypeText by lazy { itemView.findViewById<TextView>(R.id.textViewConsoleTypeOutput) }

        fun bind(player: Player) {
            nameText.text = player.name
            surnameText.text = player.surname
            bornYearText.text = player.bornYear.toString()
            consoleTypeText.text = player.consoleType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view =
            PlayerRecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerHolder(view.root)
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bind(player.get(position))
    }


    override fun getItemCount(): Int {
        return player.size
    }
}