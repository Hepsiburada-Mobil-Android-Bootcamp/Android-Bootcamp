package com.android.odevler.arifumutsepetci.data.test
import com.android.odevler.arifumutsepetci.data.model.Product
import com.android.odevler.arifumutsepetci.data.model.Store
import junit.framework.TestCase
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StoreTest{
    val store = Store(
        id = "S001",
        name = "Hepsiburada",
        products = arrayListOf(
            Product(id = "P001", name="Laptop", price = 8000, category = "elektronik", stock = 0),
            Product(id = "P002", name="Cep Telefonu", price = 5000, category = "elektronik", stock = 1),
            Product(id = "P003", name="Elma", price = 7, category = "yiyecek", stock = 10),
            Product(id = "P004", name="5L Su", price = 5, category = "icecek", stock = 5),
        ),
        date = 946728000000
    )

    @Test
    fun storeTest(){
        //success scenario

        assertTrue(store.products!![0].stock == 0)

        //fail scenario

        assertFalse(store.products!![1].stock == 0)
        assertFalse(store.products!![2].stock == 0)
        assertFalse(store.products!![3].stock == 0)
    }

    @Test
    fun productStockTest(){
        // fail scenario

        assertFalse(store.products!![0].urunStoktaVarMi())

        //success scenario

        assertTrue(store.products!![1].urunStoktaVarMi())
        assertTrue(store.products!![2].urunStoktaVarMi())
        assertTrue(store.products!![3].urunStoktaVarMi())
    }
}