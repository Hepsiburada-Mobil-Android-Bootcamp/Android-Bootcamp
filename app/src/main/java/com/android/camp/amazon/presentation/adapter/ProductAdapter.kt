package com.android.camp.amazon.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.amazon.data.model.Product
import com.android.camp.databinding.ItemProductBinding
import com.bumptech.glide.Glide
import com.sample.price.PriceTextView

class ProductAdapter(private val list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product

            Glide.with(binding.imageViewProduct.context)
                .load(product.image)
                .circleCrop()
                .into(binding.imageViewProduct)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}