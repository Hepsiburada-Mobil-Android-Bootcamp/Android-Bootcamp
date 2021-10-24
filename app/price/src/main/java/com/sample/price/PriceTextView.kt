package com.sample.price

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class PriceTextView constructor(context: Context, attributeSet: AttributeSet?) :
    ConstraintLayout(context, attributeSet) {

    var priceTextView: TextView? = null

    fun setPrice(price: Int?) {
        priceTextView?.text = price?.toString()
    }

    val priceTypes = arrayListOf("₺", "$", "€")

    init {
        val view = inflate(context, R.layout.custom_price_text_view, this)

        priceTextView = view.findViewById(R.id.text_view_price)
        val priceType: TextView by lazy { view.findViewById(R.id.text_View_price_type) }

        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.PriceTextView)

        priceTextView?.text = attrs.getString(R.styleable.PriceTextView_price)

        val type = attrs.getInt(R.styleable.PriceTextView_priceType, -1)
        priceType.text = priceTypes.getOrNull(type)

        val color = attrs.getColor(
            R.styleable.PriceTextView_priceColorTint,
            ContextCompat.getColor(context, R.color.green)
        )

        priceTextView?.setTextColor(color)
        priceType.setTextColor(color)
    }
}