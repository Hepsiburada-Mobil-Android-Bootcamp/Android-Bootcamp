package com.android.odevler.orhunozdemir.activitiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.orhunozdemir.data.Customer
import com.google.firebase.firestore.FirebaseFirestore

class AddCustomerActivitity : AppCompatActivity() {

    val customerName:EditText by lazy {findViewById<EditText>(R.id.editTextCustomerName)}
    val customerSurname:EditText by lazy { findViewById<EditText>(R.id.editTextCustomerSurname) }
    val customerAge:EditText by lazy { findViewById<EditText>(R.id.editTextCustomerAge) }
    val saveCustomerButton:Button by lazy { findViewById<Button>(R.id.buttonSaveCustomer) }
    private var firestore:FirebaseFirestore?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer_activitity)
        firestore= FirebaseFirestore.getInstance()

        saveCustomerButton.setOnClickListener {

        addCustomer()


        }

    }
    fun addCustomer()
    {
        val customer:Customer=Customer(customerName.text.toString(),
                                       customerSurname.text.toString(),
                                       customerAge.text.toString().toInt())

        firestore?.collection("orhunozdemir")?.add(customer)
            ?.addOnSuccessListener {
                Toast.makeText(this,"Save Customer Success",Toast.LENGTH_SHORT).show()


            }
            ?.addOnFailureListener{

                Toast.makeText(this,"Customer Save Failure",Toast.LENGTH_SHORT).show()
            }


    }

}