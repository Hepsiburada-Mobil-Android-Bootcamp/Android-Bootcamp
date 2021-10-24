package com.android.camp.amazon.presentation.activity

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.android.camp.R
import com.android.camp.amazon.data.network.AmazonRetrofitFactory
import com.android.camp.amazon.data.model.Product
import com.android.camp.amazon.data.model.ProductDetailModel
import com.android.camp.amazon.data.model.ProductListModel
import com.google.gson.Gson
import com.squareup.okhttp.*
import org.w3c.dom.Text
import org.xml.sax.InputSource
import retrofit2.Call
import java.io.IOException
import java.io.StringReader
import java.net.UnknownHostException
import javax.xml.parsers.DocumentBuilderFactory

class AmazonActivity : AppCompatActivity() {

    var deneme = ""
    val status: MutableLiveData<Exception> by lazy {
        MutableLiveData(null)
    }
    private val text_view_test by lazy { findViewById<TextView>(R.id.text_view_test) }
    private val layout by lazy { findViewById<ConstraintLayout>(R.id.layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amazon)

        /*
            val inputStream = assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            val fileData = String(buffer)

            val productList = Gson().fromJson(fileData, ProductListModel::class.java)

 */
        val fileDataNew = assets.open("data.json").bufferedReader().use {
            it.readText()
        }

        status.observe(this) {
            when (it) {
                is UnknownHostException -> showDialog()
            }
        }

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/11111/detail")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            val mainHandler = Handler(mainLooper)

            override fun onFailure(request: Request?, e: IOException?) {
                deneme = "sdcsdfdsf"

                text_view_test.text = "background thread"
                text_view_test.setTextColor(
                    ContextCompat.getColor(
                        this@AmazonActivity,
                        R.color.green
                    )
                )

                val textview = TextView(this@AmazonActivity)
                textview.text = "denemeeeee"
                layout.addView(textview)

                mainHandler.post {
                    status.value = e
                }
            }

            override fun onResponse(response: Response?) {
                val mainHandler = Handler(mainLooper)

                val okhttpData = response?.body()?.bytes()?.let { String(it) }

                when (response?.code()) {
                    200 -> {
                        val product = Gson().fromJson(okhttpData, Product::class.java)
                    }
                    else -> {
                        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
                        val documentBuilder = documentBuilderFactory.newDocumentBuilder()
                        val inputSource = InputSource()
                        inputSource.characterStream = StringReader(okhttpData)
                        val document = documentBuilder.parse(inputSource)

                        val code = (document.documentElement.getElementsByTagName("Code")
                            .item(0).childNodes.item(0) as Text).wholeText
                        val message = (document.documentElement.getElementsByTagName("Message")
                            .item(0).childNodes.item(0) as Text).wholeText

                        mainHandler.post {
                            showDialog(message = message, title = code)
                        }


                    }
                }

            }
        })


        retrofit()

    }

    private fun retrofit() {
        AmazonRetrofitFactory.instance.service?.getProduct()?.enqueue(object: retrofit2.Callback<ProductListModel>{
            override fun onResponse(
                call: Call<ProductListModel>,
                response: retrofit2.Response<ProductListModel>
            ) {
                if(response.isSuccessful){
                    val list = response.body()
                }else {

                }
            }

            override fun onFailure(call: Call<ProductListModel>, t: Throwable) {
                val aa = ""
            }

        })

        AmazonRetrofitFactory.instance.service?.getProductDetail(1121212)?.enqueue(object: retrofit2.Callback<ProductDetailModel>{
            override fun onResponse(
                call: Call<ProductDetailModel>,
                response: retrofit2.Response<ProductDetailModel>
            ) {
                if(response.isSuccessful){
                    val detail = response.body()
                }
            }

            override fun onFailure(call: Call<ProductDetailModel>, t: Throwable) {
                val a = ""
            }

        })
    }


    @WorkerThread
    fun test() {
        request()
    }

    @WorkerThread
    fun request() {
        (1..100000).forEach {

        }
    }

    @MainThread
    private fun showDialog(title: String? = null, message: String? = null) {
        AlertDialog.Builder(this@AmazonActivity)
            .setMessage(message)
            .setNegativeButton(
                "Vazgeç"
            ) { p0, p1 ->

            }
            .setPositiveButton(
                "Tamam"
            ) { p0, p1 ->

            }.setTitle(title)
            .create().show()
    }


    val data = "{\n" +
            "    \"products\": [\n" +
            "        {\n" +
            "            \"product_id\": \"1\",\n" +
            "            \"name\": \"Apples\",\n" +
            "            \"price\": 120,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"2\",\n" +
            "            \"name\": \"Oranges\",\n" +
            "            \"price\": 167,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/2.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"3\",\n" +
            "            \"name\": \"Bananas\",\n" +
            "            \"price\": 88,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/3.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"4\",\n" +
            "            \"name\": \"Onions\",\n" +
            "            \"price\": 110,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/4.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"5\",\n" +
            "            \"name\": \"Steak\",\n" +
            "            \"price\": 543,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/5.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"6_id_is_a_string\",\n" +
            "            \"name\": \"Pork\",\n" +
            "            \"price\": 343,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/6.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"7\",\n" +
            "            \"name\": \"Chicken\",\n" +
            "            \"price\": 272,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/chicken.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"8\",\n" +
            "            \"name\": \"Salmon\",\n" +
            "            \"price\": 267,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/8.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"9\",\n" +
            "            \"name\": \"Tuna\",\n" +
            "            \"price\": 557,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/9.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"10\",\n" +
            "            \"name\": \"Broccoli\",\n" +
            "            \"price\": 32,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/10.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"11\",\n" +
            "            \"name\": \"Bacon\",\n" +
            "            \"price\": 212,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/11.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"product_id\": \"12\",\n" +
            "            \"name\": \"Peppers\",\n" +
            "            \"price\": 9,\n" +
            "            \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/12.jpg\"\n" +
            "        }\n" +
            "    ]\n" +
            "}"
}