package com.android.odevler.abdulwahid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.abdulwahid.adapter.viewholder.SongViewHolder
import com.android.odevler.abdulwahid.data.Song

class SongAdapter(
    private val songList: ArrayList<Song>
) : RecyclerView.Adapter<SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) =
        holder.bind(songList[position])

    override fun getItemCount(): Int = songList.size
}