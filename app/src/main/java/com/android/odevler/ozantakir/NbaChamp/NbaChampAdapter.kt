package com.android.odevler.ozantakir.NbaChamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.abdulwahid.adapter.viewholder.SongViewHolder
import com.android.odevler.abdulwahid.data.Song
import com.android.odevler.ozantakir.data.NbaChamp

class NbaChampAdapter(
    private val nbaChampList: ArrayList<NbaChamp>
) : RecyclerView.Adapter<NbaChampViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbaChampViewHolder {
        return NbaChampViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_nba_champ, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NbaChampViewHolder, position: Int) =
        holder.bind(nbaChampList[position])

    override fun getItemCount(): Int = nbaChampList.size
}


