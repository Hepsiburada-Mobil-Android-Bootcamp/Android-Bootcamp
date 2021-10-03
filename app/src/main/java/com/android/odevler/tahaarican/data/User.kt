package com.android.odevler.tahaarican.data

data class User(
    var id:String? = "",
    val username:String? = "",
    val password:String? = "",
    val name:String = "",
    val surname:String = "",
    val createdDate: Long? = null
)

fun mergeNameAndSurname(user: User) : String {
    return "${user.name} ${user.surname}"
}