package com.android.camp.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FirestoreHelper {
    private var firestore: FirebaseFirestore? = null

    init {
        firestore = FirebaseFirestore.getInstance()
    }

    object Collection {
        const val aysesenses = "aysesenses"
    }

    fun getNoteQueryOrderByDate() = firestore?.collection(Collection.aysesenses)?.orderBy("date", Query.Direction.DESCENDING)
    fun getNoteQueryOrderByPriority() = firestore?.collection(Collection.aysesenses)?.orderBy("priority", Query.Direction.DESCENDING)
}