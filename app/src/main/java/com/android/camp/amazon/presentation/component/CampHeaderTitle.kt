package com.android.camp.amazon.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.camp.R

class CampHeaderTitle constructor(context: Context, attributeSet: AttributeSet?) :
    ConstraintLayout(context, attributeSet) {

    private var titleTextView: TextView? = null
    private var subTitleTextView: TextView? = null
    private var backImageView: ImageView? = null

    init {
        val view = inflate(context, R.layout.custom_view_header_title, this)
        titleTextView = view.findViewById(R.id.text_view_title)
        subTitleTextView = view.findViewById(R.id.text_view_sub_title)
        backImageView = view.findViewById(R.id.image_view_back)

        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.CampHeaderTitle)
        titleTextView?.text = attrs.getString(R.styleable.CampHeaderTitle_title)
        subTitleTextView?.text = attrs.getString(R.styleable.CampHeaderTitle_subTitle)
        backImageView?.visibility =
            attrs.getInt(R.styleable.CampHeaderTitle_isVisibilityBack, View.VISIBLE)

        attrs.recycle()
    }

    fun setTitle(title: String, subTitle: String, isVisibilityBack: Boolean) {
        titleTextView?.text = title
        subTitleTextView?.text = subTitle
        backImageView?.visibility = if (isVisibilityBack) View.VISIBLE else View.GONE
    }
}