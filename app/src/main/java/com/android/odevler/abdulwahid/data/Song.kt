package com.android.odevler.abdulwahid.data

data class Song(
    val name: String?,
    val artist: String?,
    val duration: Double,
    val year: Int?,
    var isPlaying: Boolean = false
) {
    fun playSong() {
        isPlaying = true
    }
    fun stopSong() {
        isPlaying = false
    }
}