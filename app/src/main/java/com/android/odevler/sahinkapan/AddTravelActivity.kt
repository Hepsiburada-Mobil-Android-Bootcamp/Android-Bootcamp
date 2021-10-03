package com.android.odevler.sahinkapan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.camp.R
import com.android.odevler.sahinkapan.ticket.Ticket
import com.google.firebase.firestore.FirebaseFirestore

class AddTravelActivity : AppCompatActivity() {

    private val btnSave by lazy { findViewById<Button>(R.id.btnSave) }

    private val editId by lazy { findViewById<EditText>(R.id.editID) }
    private val editVehicle by lazy { findViewById<EditText>(R.id.editVehicle) }
    private val editFrom by lazy { findViewById<EditText>(R.id.editFrom) }
    private val editTo by lazy { findViewById<EditText>(R.id.editTo) }
    private val editPrice by lazy { findViewById<EditText>(R.id.editPrice) }


    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_travel)
        firestore= FirebaseFirestore.getInstance()


        btnSave.setOnClickListener{
            if(valid()){
                saveTicket()
            }
        }

    }


    private fun saveTicket() {
        val ticket=Ticket(
            travelID = editId.text.toString(),
            vehicleType = editVehicle.text.toString(),
            fromCity = editFrom.text.toString(),
            toCity = editTo.text.toString(),
            price = editPrice.text.toString())

        firestore?.collection("sahinkapan")?.add(ticket)?.addOnCompleteListener {task->
            when(task.isSuccessful){
                true->{
                    finish()
                }
                false->{
                    Toast.makeText(this,"Kaydedilemedi...",Toast.LENGTH_SHORT).show()
                }
            }
        }
            ?.addOnFailureListener {
                Log.e("HATA",it.message.toString())
                Toast.makeText(this,"Kaydedilemedi...",Toast.LENGTH_SHORT).show()
            }

    }


    private fun valid(): Boolean {
        val id = editId.text
        val vehicle=editVehicle.text
        val from=editFrom.text
        val to=editTo.text
        val price =editPrice.text

        if (id.isNullOrEmpty()) {

            Toast.makeText(this,"bBoş olamaz...",Toast.LENGTH_SHORT).show()

            return false
        }else{
            return true
        }


    }


}