package com.android.camp.amazon.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.camp.R

class FavoriteProductFragment : Fragment() {

    val args:FavoriteProductFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_favorite, container, false)

        view.findViewById<TextView>(R.id.text_view_sub_title).text = "${args.productId} ürünü favorilere eklendi..."

        return view
    }
}