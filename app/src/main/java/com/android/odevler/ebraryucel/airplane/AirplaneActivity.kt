package com.android.odevler.ebraryucel.airplane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.ebraryucel.data.Airplane
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class AirplaneActivity : AppCompatActivity() {

    val recyclerAirplane by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private lateinit var firestore:FirebaseFirestore
    val airplanes : ArrayList<Airplane> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_airplane)

        firestore= Firebase.firestore

        downloadData()


    }





    fun fabOnClick(view: View){
        val intent=Intent(this,AddAirplaneActivity::class.java)
        startActivity(intent)
    }

    private fun downloadData(){ //firebase'de ebraryucel collection'undaki verileri çekip recyclerview'e aktaran fonksiyon
        firestore.collection("ebraryucel").addSnapshotListener { value, error ->

            if(error!=null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                if(value!=null){
                    if(!value.isEmpty){

                        airplanes.clear()

                        val documents=value.documents

                        for(document in documents){

                            val manufacturer = document.get("manifacturer") as? String
                            val model=document.get("model") as? String
                            val owner=document.get("owner") as? String
                            val capacity=document.get("capacity") as? String

                            val airplane=Airplane(manufacturer,model,owner,capacity?.toInt())
                            airplanes.add(airplane)


                        }

                        initAdapter(airplanes)

                    }
                    else{
                       Toast.makeText(this,"Empty value!",Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }

    private fun initAdapter(airplanes:ArrayList<Airplane>){ //recyclerview adaptörünü başlatan fonksiyon

        recyclerAirplane.layoutManager=LinearLayoutManager(this)
        val adapter=RecyclerAdapter(airplanes)
        recyclerAirplane.adapter=adapter

    }



}