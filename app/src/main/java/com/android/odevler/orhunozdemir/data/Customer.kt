package com.android.odevler.orhunozdemir.data

 data class Customer (var name:String?="",var surname:String?="",var age:Int?=0)
 {
     fun fullname():String= "$name-$surname"

}