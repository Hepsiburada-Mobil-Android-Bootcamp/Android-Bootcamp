package com.android.odevler.mehmet_serkan_guzel

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.mehmet_serkan_guzel.data.Football

class FootballViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textViewFootballName: TextView by lazy {
        view.findViewById(R.id.text_view_footballName)
    }
    private val textViewFootballYear: TextView by lazy {
        view.findViewById(R.id.text_view_footballYear)
    }
    private val textViewFootballBestPlayer: TextView by lazy {
        view.findViewById(R.id.text_view_bestPlayer)
    }
    private val textViewFootballTeamColor: TextView by lazy {
        view.findViewById(R.id.text_view_teamColor)
    }

    @SuppressLint("SetTextI18n")
    fun bind(football: Football) {
        textViewFootballName.text = "Takım adı: " + football.teamName
        textViewFootballYear.text = "Kuruluş Yılı: " + football.year.toString()
        textViewFootballBestPlayer.text = "En iyi oyuncusu: " + football.bestPlayer
        textViewFootballTeamColor.text = "Takım rengi: " + football.teamColor
    }

}