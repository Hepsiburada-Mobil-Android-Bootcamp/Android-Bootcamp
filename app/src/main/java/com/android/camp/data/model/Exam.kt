package com.android.camp.data.model

import com.yasincetin.firebasesdk.firestore.FirestoreModel

data class Exam(var id: String = "", val name: String? = "", val date: Long? = null) :
    FirestoreModel()
