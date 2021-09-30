package com.android.odevler.oguzhanyildirim.data.model

data class Hospital(
    val name: String = "X Hospital",
    val address: String = "X Location",
    val contactNumber: String = "0000000",
    var capacity: String = "9999"
    ) {

    fun increaseCapacity(additionalCapacity: Int){
        this.capacity = (this.capacity.toInt() + additionalCapacity).toString()
    }

}
