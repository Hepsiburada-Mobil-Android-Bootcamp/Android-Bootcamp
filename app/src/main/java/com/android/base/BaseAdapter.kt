package com.android.base

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.yasincetin.firebasesdk.firestore.FirestoreAdapter
import com.yasincetin.firebasesdk.firestore.FirestoreModel

abstract class BaseAdapter<VH : RecyclerView.ViewHolder, M : FirestoreModel>(query: Query?) :
    FirestoreAdapter<VH, M>(query)