package com.android.odevler.ayse_senses.data.model

import com.yasincetin.firebasesdk.firestore.FirestoreModel
import java.text.SimpleDateFormat
import java.util.*

data class Note(
    val title: String? = null,
    val priority: Int? = null,
    val description: String? = null,
    val date: Long? = null): FirestoreModel()

fun Note.convertLongToTime(): String? {
    val newDate = this.date?.let { Date(it) }
    val format = SimpleDateFormat("dd/MM/yyyy hh:mm")
    return format.format(newDate)
}
