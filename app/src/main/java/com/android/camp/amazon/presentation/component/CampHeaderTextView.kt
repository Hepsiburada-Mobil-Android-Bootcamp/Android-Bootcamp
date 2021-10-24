package com.android.camp.amazon.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.android.camp.R

class CampHeaderTextView constructor(context: Context, attributeSet: AttributeSet?) : AppCompatTextView(context, attributeSet) {
    init {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.title_h1_size))
        setTextColor(ContextCompat.getColor(context, R.color.green))
    }
}