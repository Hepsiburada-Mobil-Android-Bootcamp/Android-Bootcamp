package com.android.odevler.ozantakir.data

data class NbaChamp(
    val teamName: String? = "",
    val championshipYear: Int,
    val finalsMvp: String? = "",
    val gamesPlayedInFinals: Int
) {
    fun nbaChamp(): String {
        return "$teamName has won championship."
    }


}
