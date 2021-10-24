package com.android.odevler.mehmet_serkan_guzel

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.camp.R
import com.android.odevler.mehmet_serkan_guzel.data.Football
import com.google.firebase.firestore.Query
import com.yasincetin.firebasesdk.firestore.FirestoreAdapter


class FootballAdapter(private val query: Query?) :
    FirestoreAdapter<FootballViewHolder, Football>(query) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FootballViewHolder {
        return FootballViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_football, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FootballViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getModelClass() = Football::class.java
}
