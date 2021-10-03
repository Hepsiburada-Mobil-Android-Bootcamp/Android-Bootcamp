package com.android.odevler.orhunozdemir.activitiy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.orhunozdemir.adapter.CustomerListAdapter
import com.android.odevler.orhunozdemir.data.Customer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CustomerListActivity : AppCompatActivity() {
     private val fab:FloatingActionButton by lazy { findViewById<FloatingActionButton>(R.id.floatingActionButtonAddCustomer) }
     private val rv:RecyclerView by lazy { findViewById<RecyclerView>(R.id.customerRecyclerView) }
     private lateinit var adapter:CustomerListAdapter
      var dataSet=ArrayList<Customer>()
     private  var firestore: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)
        firestore=FirebaseFirestore.getInstance()
        initManager()
        getCustomers()


        fab.setOnClickListener {


            startActivity(Intent(this,AddCustomerActivitity::class.java))
        }
    }

    private fun initManager() {
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getCustomers(){

        firestore?.collection("orhunozdemir")?.addSnapshotListener {value ,error ->

           value?.forEach{queryDocumentSnapshot ->
               queryDocumentSnapshot.toObject(Customer::class.java).also { value ->
               dataSet.add(value)

           }

           }
            rv.adapter=CustomerListAdapter(this ,dataSet)
        }
         //adapter=





    }
}