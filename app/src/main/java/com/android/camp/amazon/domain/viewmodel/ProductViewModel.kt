package com.android.camp.amazon.domain.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.camp.amazon.data.model.Product
import com.android.camp.amazon.data.model.ProductListModel
import com.android.camp.amazon.data.network.AmazonRetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ProductViewModel : ViewModel() {
    private val _list = MutableLiveData<List<Product>>()
    val list get() = _list

    fun getProductList() {

        AmazonRetrofitFactory.instance.service?.getProduct()
            ?.enqueue(object : retrofit2.Callback<ProductListModel> {
                override fun onResponse(
                    call: Call<ProductListModel>,
                    response: Response<ProductListModel>
                ) {
                    val productListModel = response.body()

                    _list.value = productListModel?.products?.filter { it.price ?: 0 > 200 }
                }

                override fun onFailure(call: Call<ProductListModel>, t: Throwable) {

                }

            })
    }

}