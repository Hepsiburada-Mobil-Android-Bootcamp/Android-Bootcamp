package com.android.firebase.auth.google.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.camp.R
import com.bumptech.glide.Glide

object GoogleBind {

    @JvmStatic
    @BindingAdapter("bindGoogleEmail")
    fun TextView.bindGoogleEmail(email: String) {
        text = resources.getString(R.string.google_user_email, email)
    }

    @JvmStatic
    @BindingAdapter("bindGooglePhoneNumber")
    fun TextView.bindGooglePhoneNumber(phoneNumber: String?) {
        text = when (phoneNumber) {
            null -> resources.getString(R.string.google_user_phone, phoneNumber)
            else -> resources.getString(R.string.google_user_phone_null)
        }

    }

    @JvmStatic
    @BindingAdapter("bindGoogleImage")
    fun ImageView.bindGoogleImage(url: String) {
        Glide.with(this.context)
            .load(url)
            .centerCrop()
            .into(this)
    }

}