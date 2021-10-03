package com.android.odevler.damlaAlpan.car

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.damlaAlpan.car.CarAdapter
import com.android.odevler.damlaAlpan.data.Car
import com.google.firebase.firestore.FirebaseFirestore

class CarsActivity : AppCompatActivity() {
    val recyclerViewCar by lazy { findViewById<RecyclerView>(R.id.recycler_view_car)}
    val addNewCarButton by lazy { findViewById<View>(R.id.btnSave) }

    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        addNewCarButton.setOnClickListener {
            val intent = Intent(this, AddNewCarActivity::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()

        initCar()
        bindCar()
    }
    private fun initCar(){
        recyclerViewCar.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
    private fun bindCar(){
        firestore?.collection("damlaAlpan")?.addSnapshotListener{value,error ->
            val list = arrayListOf<Car>()

            value?.forEach{queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Car::class.java).also { car ->
                    list.add(car)

                }
            }
            recyclerViewCar.adapter = CarAdapter(list)
        }
    }
}