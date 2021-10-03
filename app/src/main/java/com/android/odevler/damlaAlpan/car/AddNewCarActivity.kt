package com.android.odevler.damlaAlpan.car

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.damlaAlpan.data.Car
import com.google.firebase.firestore.FirebaseFirestore


class AddNewCarActivity : AppCompatActivity() {
    private val buttonKaydet by lazy {findViewById<View>(R.id.btnSave)}
    private val editTextCarBrand by lazy {findViewById<EditText>(R.id.txtBrand)}
    private val editTextCarModel by lazy {findViewById<EditText>(R.id.txtModel)}
    private val editTextCarFuelType by lazy {findViewById<EditText>(R.id.txtFuelType)}


    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_car)

        buttonKaydet.setOnClickListener{
            if(isValid()){
                save()
            }
        }
        firestore = FirebaseFirestore.getInstance()
    }


    fun EditText.isValid(): Boolean {
        if (text.isNullOrEmpty()) {
            requestFocus()
            error = "gerekli!"
        }

        return text.isNotEmpty()
    }

    private fun isValid(): Boolean {
        var isValid: Boolean

        isValid = editTextCarBrand.isValid()
        isValid = editTextCarModel.isValid()
        isValid = editTextCarFuelType.isValid()
       // isValid = editTextCarSeatCapacity.toString().isValid()


        return isValid
    }
    private fun save() {
        val car = Car(
            brand = editTextCarBrand.text.toString(),
            model = editTextCarModel.text.toString(),
            fuelType = editTextCarFuelType.text.toString()
        )
        // seatCapacity = editTextCarSeatCapacity.text.toString().toInt())
        firestore?.collection("damlaAlpan")?.add(car)?.addOnCompleteListener { task ->
            when (task.isSuccessful) {
                true -> finish()
                false -> Toast.makeText(this, "Car cant created!", Toast.LENGTH_LONG).show()
            }
        }

    }
}