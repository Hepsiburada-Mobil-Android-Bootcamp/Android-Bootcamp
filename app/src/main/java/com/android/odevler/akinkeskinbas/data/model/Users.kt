package com.android.odevler.akinkeskinbas.data.model

import java.io.Serializable

data class Users(var name: String? = "", var number: Int? = 0, var mail:String?="", var profilUrl:String?="", var yetkiliMi:Boolean?=false,  val date: Long? = null)
