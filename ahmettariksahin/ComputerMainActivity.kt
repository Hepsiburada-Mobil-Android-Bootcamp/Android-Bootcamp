package com.android.odevler.ahmettariksahin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.odevler.ahmettariksahin.computer.AddComputer
import com.android.odevler.ahmettariksahin.computer.ComputerAdapter
import com.android.odevler.ahmettariksahin.model.Computer
import com.google.firebase.firestore.FirebaseFirestore

class ComputerMainActivity:AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.computers) }
    private val addComputertButton by lazy { findViewById<Button>(R.id.addComputer) }

    var firestore: FirebaseFirestore? = null

    val computers = arrayListOf<Computer>(
        Computer("500w Gold+","AOC","ASUS","16GB Corsair", "RAZER"),
        Computer("600w Gold+","ASUS","MSI","32GB Corsair", "RAMPAGE"),
        Computer("750w Gold+","HP","MSI","8GB Corsair", "STEELSERIES"),
        Computer("450w Gold+","DELL","ASUS","32GB Corsair", "RAZER"),
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computer_main)
        addComputertButton.setOnClickListener {
            val intent = Intent(this,AddComputer::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()
        initComputeers()
        bindComputeers()

    }

    private fun initComputeers(){
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
    private fun bindComputeers(){
        firestore?.collection("ahmettariksahin")?.addSnapshotListener { value, _ ->
            val list = arrayListOf<Computer>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Computer::class.java).also { computers ->
                    list.add(computers)
                }
            }

            recyclerView.adapter = ComputerAdapter(list)
        }

    }
}