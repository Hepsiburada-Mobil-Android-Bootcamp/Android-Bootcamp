package com.android.camp.amazon.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.camp.R

class ProductDetailsFragment : Fragment() {

    val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        view.findViewById<TextView>(R.id.text_view_title).apply {
            text = "id: ${args.productId}"
            setOnClickListener {

                val direction = object :NavDirections{
                    override fun getActionId(): Int {
                       return R.id.action_productDetailsFragment_to_addFavoriteFragment
                    }

                    override fun getArguments(): Bundle {
                        return Bundle().apply {
                            putInt("id", 1234)
                        }
                    }

                }

                findNavController().navigate(ProductDetailsFragmentDirections.actionProductDetailsFragmentToAddFavoriteFragment(args.productId))
            }
        }

        view.findViewById<View>(R.id.button_delete).setOnClickListener {
            findNavController().navigate(ProductDetailsFragmentDirections.actionProductDetailsFragmentToDeleteProductFragment())
        }

        view.findViewById<View>(R.id.image_view_back).setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}