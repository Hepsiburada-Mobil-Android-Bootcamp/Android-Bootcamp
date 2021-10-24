package com.android.odevler.mehmet_serkan_guzel.data

import com.yasincetin.firebasesdk.firestore.FirestoreModel

data class Football(
    val teamName: String? = "",
    val year: Int = 0,
    val bestPlayer: String? = "",
    val teamColor: String? = "",
): FirestoreModel()
{
    fun yearToString(): String {
        return year.toString()
    }
}

