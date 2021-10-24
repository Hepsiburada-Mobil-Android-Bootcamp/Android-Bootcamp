package com.android.camp.amazon.data.network

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import java.util.concurrent.TimeUnit

class AmazonRetrofitFactory  private  constructor() {

    var service: AmazonService? = null

    init {
        val client = okhttp3.OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .connectTimeout(100000, TimeUnit.SECONDS)
            .readTimeout(100000, TimeUnit.SECONDS)
            .writeTimeout(100000, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(JaxbConverterFactory.create())
            .build()

        service = retrofit.create(AmazonService::class.java)
    }

    companion object {
       private var INSTANCE: AmazonRetrofitFactory? = null

        val instance: AmazonRetrofitFactory
            get()  {
                if(INSTANCE == null) {
                    INSTANCE = AmazonRetrofitFactory()
                }


                return INSTANCE!!
            }
    }
}