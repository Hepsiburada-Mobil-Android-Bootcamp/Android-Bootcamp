package com.android.camp.amazon.domain.presenter

import com.android.camp.amazon.data.model.Product
import com.android.camp.amazon.data.model.ProductListModel
import com.android.camp.amazon.data.network.AmazonRetrofitFactory
import com.android.camp.amazon.presentation.view.ProductView
import retrofit2.Call
import retrofit2.Response

class ProductPresenter {

    var productView: ProductView? = null

    fun getProductList() {
        AmazonRetrofitFactory.instance.service?.getProduct()
            ?.enqueue(object : retrofit2.Callback<ProductListModel> {
                override fun onResponse(
                    call: Call<ProductListModel>,
                    response: Response<ProductListModel>
                ) {
                    val productListModel = response.body()

                    val list = productListModel?.products?.filter { it.price ?: 0 > 200 }

                    list?.let { productView?.setProducts(it) }
                }

                override fun onFailure(call: Call<ProductListModel>, t: Throwable) {

                }

            })
    }
}