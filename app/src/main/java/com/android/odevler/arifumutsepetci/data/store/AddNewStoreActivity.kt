package com.android.odevler.arifumutsepetci.data.store

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.camp.R
import com.android.odevler.arifumutsepetci.data.model.Store
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class AddNewStoreActivity : AppCompatActivity() {
    private val buttonStoreSave by lazy { findViewById<View>(R.id.button_store_save) }
    private val editTextStoreId by lazy { findViewById<EditText>(R.id.edit_text_store_id) }
    private val editTextStoreName by lazy { findViewById<EditText>(R.id.edit_text_store_name) }
    private val editTextStoreAddress by lazy { findViewById<EditText>(R.id.edit_text_store_address) }

    private var firestore: FirebaseFirestore? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_store)

        buttonStoreSave.setOnClickListener {
            if (valid()) {
                save()
            }
        }
        firestore = FirebaseFirestore.getInstance()
    }
    private fun valid(): Boolean {
        var valid = false;

        val id = editTextStoreId.text
        val name = editTextStoreName.text
        val address = editTextStoreAddress.text

        when {
            id.isNullOrEmpty() -> {
                editTextStoreId.requestFocus()
                editTextStoreId.error = "Dükkan ID'si boş olamaz."
            }
            name.isNullOrEmpty() -> {
                editTextStoreName.requestFocus()
                editTextStoreName.error = "Dükkan ismi boş olamaz."
            }
            address.isNullOrEmpty() -> {
                editTextStoreAddress.requestFocus()
                editTextStoreAddress.error = "Dükkan adresi boş olamaz."
            }
            else -> valid = true
        }
        return valid
    }

    private fun save() {
        val store =
            Store(date = Calendar.getInstance().time.time, name = editTextStoreName.text.toString(), address = editTextStoreAddress.text.toString(), id = editTextStoreId.text.toString())

        firestore?.collection("arifumutsepetci")?.add(store)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Dükkan Eklenemedi...", Toast.LENGTH_LONG).show()
            }
        }
    }
}