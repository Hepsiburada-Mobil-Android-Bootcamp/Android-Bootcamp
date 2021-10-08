package com.android.camp.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.android.camp.R
import com.android.camp.address.data.Address
import com.google.firebase.firestore.FirebaseFirestore

class AddAddress : AppCompatActivity() {

    private val AutoText by lazy { findViewById<AutoCompleteTextView>(R.id.autoText) }
    private val AutoText2 by lazy { findViewById<AutoCompleteTextView>(R.id.autoText2) }
    private val EditText by lazy { findViewById<android.widget.EditText>(R.id.editText) }
    private val EditText2 by lazy { findViewById<android.widget.EditText>(R.id.editText2) }
    private val buttonSave by lazy { findViewById<View>(R.id.button) }
    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)


        buttonSave.setOnClickListener {
            save()
        }



        val AutoText by lazy { findViewById<AutoCompleteTextView>(R.id.autoText) }
        val AutoText2 by lazy { findViewById<AutoCompleteTextView>(R.id.autoText2) }


        val ilListesi = resources.getStringArray(R.array.iller)
        val ilAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, ilListesi)
        val ilceListesi = resources.getStringArray((R.array.ilceler))
        val ilceAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, ilceListesi)


        AutoText.setAdapter(ilAdapter)
        AutoText.threshold = 1

        AutoText2.setAdapter(ilceAdapter)
        AutoText2.threshold = 1

        firestore = FirebaseFirestore.getInstance()


    }



    private fun save() {
        val address = Address(
            city = AutoText.text.toString(),
            district = AutoText2.text.toString(),
            addressDetails = EditText.text.toString(),
            addressType = EditText2.text.toString()
        )

        if (address.city.isNullOrEmpty() || address.district.isNullOrEmpty()
            || address.addressDetails.isNullOrEmpty() || address.addressType.isNullOrEmpty()) {
            Toast.makeText(this,"BOŞ ALANLARI DOLDURUNUZ..", Toast.LENGTH_LONG).show()
        } else { firestore?.collection("address")?.add(address)?.addOnSuccessListener {
            Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show()
            finish()
        }?.addOnFailureListener {
            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
        }
        }
    }

}


