package com.android.odevler.ebraryucel.data


//data class Airplane(val manufacturer:String,val model:String, val owner:String,val downloadUrl:String,val capacity:Int){
data class Airplane(val manufacturer:String?,val model:String?, val owner:String?,val capacity:Int?){

    fun info():String{
        return "Airplane manufacturer : ${manufacturer} model : ${model} owner : ${owner}  capacity : ${capacity}"
    }

}
