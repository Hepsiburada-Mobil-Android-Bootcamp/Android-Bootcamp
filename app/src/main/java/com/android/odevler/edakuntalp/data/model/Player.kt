package com.android.odevler.edakuntalp.data.model

data class Player(val name:String?,val surname:String?,val bornYear:Int?,val consoleType:String?) {

    fun createUsername(): String {
        return surname + name + bornYear.toString()
    }
}