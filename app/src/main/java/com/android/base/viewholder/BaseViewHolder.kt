package com.android.base.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yasincetin.firebasesdk.firestore.FirestoreModel

abstract class BaseViewHolder<T : FirestoreModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(model: T?) {
        model?.let {
            toBind(it)
        }
    }

    open fun toBind(model: T) {
        Log.d("", "")
    }
}