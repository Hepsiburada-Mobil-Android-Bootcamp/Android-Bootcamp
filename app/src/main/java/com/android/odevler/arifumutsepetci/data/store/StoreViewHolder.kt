package com.android.odevler.arifumutsepetci.data.store

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.android.camp.R
import com.android.odevler.arifumutsepetci.data.model.Store

class StoreViewHolder(view: View) :RecyclerView.ViewHolder(view) {
    private val textViewStoreName: TextView by lazy { view.findViewById(R.id.text_view_store_name) }
    private val layoutStoreRoot: View by lazy { view.findViewById(R.id.layout_store_root) }

    fun bind(store: Store) {
        textViewStoreName.text = store.name
    }
}