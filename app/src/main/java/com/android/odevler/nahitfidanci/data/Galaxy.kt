package com.android.odevler.nahitfidanci.data


data class Galaxy(
    val name: String? = "",
    val lightYearDistance: Int? = 0,
    var knownNumOfStars: Long? = 0,
    val exploredYear: Int? = 0
) {
    fun exploreNewStar(): Long {
        return knownNumOfStars!! + 1;
    }
}
