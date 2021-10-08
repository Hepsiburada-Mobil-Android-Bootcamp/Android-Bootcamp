package com.android.odevler.arifumutsepetci.data.store

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.camp.databinding.ActivityAddNewStoreBinding
import androidx.appcompat.app.AppCompatActivity
import com.android.camp.R
import com.android.odevler.arifumutsepetci.data.model.Store
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddNewStoreActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null

    private var binding:ActivityAddNewStoreBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_store)
        binding?.buttonStoreSave?.setOnClickListener{
            if(valid()){
                save()
            }
        }
        firestore = FirebaseFirestore.getInstance()
    }
    private fun valid(): Boolean {
        var valid = false

        val id = binding?.editTextStoreId?.text
        val name = binding?.editTextStoreName?.text
        val address = binding?.editTextStoreAddress?.text

        when {
            id.isNullOrEmpty() -> {
                binding?.editTextStoreId?.requestFocus()
                binding?.editTextStoreId?.error = "Dükkan ID'si boş olamaz."
            }
            name.isNullOrEmpty() -> {
                binding?.editTextStoreName?.requestFocus()
                binding?.editTextStoreName?.error = "Dükkan ismi boş olamaz."
            }
            address.isNullOrEmpty() -> {
                binding?.editTextStoreAddress?.requestFocus()
                binding?.editTextStoreAddress?.error = "Dükkan adresi boş olamaz."
            }
            else -> valid = true
        }
        return valid
    }

    private fun save() {
        val store =
            Store(date = Calendar.getInstance().time.time, name = binding?.editTextStoreName?.text.toString(), address = binding?.editTextStoreAddress?.text.toString(), id = binding?.editTextStoreId?.text.toString())

        firestore?.collection("arifumutsepetci")?.add(store)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Dükkan Eklenemedi...", Toast.LENGTH_LONG).show()
            }
        }
    }
}