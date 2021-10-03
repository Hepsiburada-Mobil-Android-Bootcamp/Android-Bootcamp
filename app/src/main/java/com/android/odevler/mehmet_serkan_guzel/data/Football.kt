package com.android.odevler.mehmet_serkan_guzel.data

data class Football(
    val teamName: String? = "",
    val year: Int = 0,
    val bestPlayer: String? = "",
    val teamColor: String? = "",
)
{
    fun yearToString(): String {
        return year.toString()
    }
}

