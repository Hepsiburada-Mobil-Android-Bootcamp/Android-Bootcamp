package com.android.odevler.ozantakir.NbaChamp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Question
import com.android.odevler.ozantakir.data.NbaChamp

class NbaChampAdapter (val nbaChamp:ArrayList<NbaChamp>):RecyclerView.Adapter<NbaChampAdapter.nbaChampHolder>() {

    inner class nbaChampHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamNameOut by lazy { itemView.findViewById<TextView>(R.id.team_name_out) }
        private val yearOut by lazy { itemView.findViewById<TextView>(R.id.year_out) }
        private val mvpOut by lazy { itemView.findViewById<TextView>(R.id.mvp_out) }
        private val gamesOut by lazy { itemView.findViewById<TextView>(R.id.games_out) }

        fun bind(nbaChamp: NbaChamp) {
            teamNameOut.text = nbaChamp.teamName
            yearOut.text = nbaChamp.championshipYear.toString()
            mvpOut.text = nbaChamp.finalsMvp
            gamesOut.text = nbaChamp.gamesPlayedInFinals.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nbaChampHolder {

        return nbaChampHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_nba_champ_adapter,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: nbaChampHolder, position: Int) {
        holder.bind(nbaChamp.get(position))
    }

    override fun getItemCount(): Int {
        return nbaChamp.size
    }
}