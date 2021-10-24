package com.android.camp.amazon.data.network

import com.android.camp.amazon.data.model.ProductDetailModel
import com.android.camp.amazon.data.model.ProductListModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AmazonService {

    @GET("list")
    fun getProduct(): Call<ProductListModel>

    @GET("{ticket_id}/detail")
    fun getProductDetail(@Path("ticket_id") ticketId: Int): Call<ProductDetailModel>

    @GET("/")
    fun getMovies(@Query("s") type:String, @Query("apikey") apikey:String, @Query("page") page:Int): Call<ResponseBody>
}