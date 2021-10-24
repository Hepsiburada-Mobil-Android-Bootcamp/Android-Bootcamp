package com.android.odevler.akinkeskinbas.data.model

import com.yasincetin.firebasesdk.firestore.FirestoreModel

data class Users(
    var name: String? = "",
    var number: Int? = 0,
    var mail: String? = "",
    var profilUrl: String? = "",
    var yetkiliMi: Boolean? = false,
    val date: Long? = null
) : FirestoreModel()
