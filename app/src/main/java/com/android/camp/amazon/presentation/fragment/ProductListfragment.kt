package com.android.camp.amazon.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.amazon.data.model.Product
import com.android.camp.amazon.domain.presenter.ProductPresenter
import com.android.camp.amazon.domain.viewmodel.ProductViewModel
import com.android.camp.amazon.presentation.adapter.ProductAdapter
import com.android.camp.amazon.presentation.component.CampHeaderTitle
import com.android.camp.amazon.presentation.view.ProductView

class ProductListfragment : Fragment(), ProductView {

    val presenter = ProductPresenter()
    var recyclerViewProductList:RecyclerView? = null

    val productViewModel by viewModels<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        view.setOnClickListener {

            /* findNavController().navigate(R.id.productDetailsFragment, Bundle().apply {
                 putInt("id", 1234)
             })

             */

            findNavController().navigate(
                ProductListfragmentDirections.actionProductListfragmentToProductDetailsFragment(
                    5678
                )
            )

        }
        view.findViewById<View>(R.id.fab_add_product).setOnClickListener {
            findNavController().navigate(R.id.action_productListfragment_to_addNewProductFragment)
        }

        val camp_header_title = view.findViewById<CampHeaderTitle>(R.id.camp_header_title)
        //  camp_header_title.setTitle("Ürün Listesi", "ürünler..", false)

        recyclerViewProductList = view.findViewById<RecyclerView>(R.id.recycler_view_product_list)

        presenter.productView = this

        productViewModel.list.observe(requireActivity()) { list ->
            recyclerViewProductList?.adapter = ProductAdapter(list)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel.getProductList()
       // presenter.getProductList()
    }

    override fun setProducts(list: List<Product>) {
        recyclerViewProductList?.adapter = ProductAdapter(list)
    }
}