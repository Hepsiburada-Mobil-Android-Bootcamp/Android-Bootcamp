package com.android.odevler.oguzhanyildirim.data

data class Hospital(
    val id: String,
    val name: String,
    val contactNumber: String,
    var capacity: Int,
    val address: String
    ) {

    fun increaseCapacity(additionalCapacity: Int){
        this.capacity+=additionalCapacity
    }

}
