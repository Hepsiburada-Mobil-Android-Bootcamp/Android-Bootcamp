package com.android.odevler.abdulwahid.data

data class Song(
    val name: String? = null,
    val artist: String? = null,
    val duration: Double? = null,
    val year: Int? = null,
    var isPlaying: Boolean = false
) {
    fun playSong() {
        isPlaying = true
    }

    fun stopSong() {
        isPlaying = false
    }
}