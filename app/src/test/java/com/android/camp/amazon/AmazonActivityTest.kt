package com.android.camp.amazon

import com.android.camp.amazon.data.model.Product
import com.android.camp.amazon.data.model.ProductDetailModel
import com.android.camp.amazon.data.model.ProductListModel
import com.google.gson.Gson
import junit.framework.TestCase
import org.junit.Test

class AmazonActivityTest : TestCase(){

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


    val productData = "{\"image\":\"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg\",\"name\":\"Apples\",\"price\":120,\"product_id\":\"1\"}"

    val productDetailData = "{\n" +
            "    \"product_id\": \"1\",\n" +
            "    \"name\": \"Apples\",\n" +
            "    \"price\": 120,\n" +
            "    \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg\",\n" +
            "    \"description\": \"An apple a day keeps the doctor away.\"\n" +
            "}"

    val productDetailFailData = "{\n" +
            "    \"productId\": \"1\",\n" +
            "    \"name\": \"Apples\",\n" +
            "    \"price\": 120,\n" +
            "    \"image\": \"https://s3-eu-west-1.amazonaws.com/developer-application-test/images/1.jpg\",\n" +
            "    \"description\": \"An apple a day keeps the doctor away.\"\n" +
            "}"

    @Test
    fun testFromJsonConverter(){
        val list = Gson().fromJson(data, ProductListModel::class.java)

       assertTrue(list.products?.size == 12)


    }

    @Test
    fun testToJsonConverter(){
        val list = Gson().fromJson(data, ProductListModel::class.java)
        val product = list.products?.first()

        assertTrue(product?.productId == "1")

        val jsonString = Gson().toJson(product)

        val productNew = Gson().fromJson(productData, Product::class.java)
        assertTrue(product?.productId == productNew.productId)

    }

    @Test
    fun testFromJsonToDetailsConverter(){
        val product = Gson().fromJson(productDetailData, ProductDetailModel::class.java)

        assertTrue(product.urunId == "1")
        assertFalse(product.urunId == "10")
    }

    @Test
    fun testFromJsonToDetailConverterFailScenario(){
        val product = Gson().fromJson(productDetailFailData, ProductDetailModel::class.java)
        assertTrue(product.urunId == null)
        assertFalse(product.urunId == "1")
    }

}