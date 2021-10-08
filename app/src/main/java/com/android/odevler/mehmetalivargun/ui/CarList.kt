package com.android.odevler.mehmetalivargun.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.MainActivity
import com.android.camp.R
import com.android.camp.databinding.ActivityCarListBinding
import com.android.odevler.mehmetalivargun.adapter.CarAdapter
import com.android.odevler.mehmetalivargun.data.Car
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CarList : AppCompatActivity() {
    private var binding:ActivityCarListBinding ? =null
    var fireStore:FirebaseFirestore?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car_list)

        fireStore= FirebaseFirestore.getInstance()
        getCarsFireStore()
        binding?.fabAdd?.setOnClickListener {
            startActivity(Intent(this,AddCarActivity::class.java))

        }
    }

    private fun getCarsFireStore() {
        fireStore?.collection("mehmetalivargun")?.addSnapshotListener { value, error ->
            val list= arrayListOf<Car>()
            value?.forEach {
                it -> it.toObject(Car::class.java).also { car->
                    list.add(car)
            }
            }
            binding?.recyclerViewCar?.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            binding?.recyclerViewCar?.adapter=CarAdapter(context = this,list)

        }
    }
}