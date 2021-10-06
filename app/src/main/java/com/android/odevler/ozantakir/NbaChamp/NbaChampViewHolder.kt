package com.android.odevler.ozantakir.NbaChamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.abdulwahid.data.Song
import com.android.odevler.ozantakir.data.NbaChamp

class NbaChampViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val teamNameOut by lazy { itemView.findViewById<TextView>(R.id.team_name_out) }
    private val yearOut by lazy { itemView.findViewById<TextView>(R.id.year_out) }
    private val finalsMvpOut by lazy { itemView.findViewById<TextView>(R.id.mvp_out) }
    private val gamesPlayedOut by lazy { itemView.findViewById<TextView>(R.id.games_out) }

    fun bind(nbaChamp: NbaChamp) {
        teamNameOut.text = nbaChamp.teamName
        yearOut.text = nbaChamp.championshipYear.toString()
        finalsMvpOut.text = nbaChamp.finalsMvp
        gamesPlayedOut.text = nbaChamp.gamesPlayedInFinals.toString()


    }

}