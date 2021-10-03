package com.android.camp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.data.model.Exam
import com.android.camp.exam.AddNewExamActivity
import com.android.camp.exam.ExamAdapter
import com.android.camp.question.QuestionsActivity
import com.android.odevler.arifumutsepetci.data.model.Store
import com.android.odevler.arifumutsepetci.data.store.AddNewStoreActivity
import com.android.odevler.arifumutsepetci.data.store.StoreAdapter
import com.google.firebase.firestore.FirebaseFirestore


class StoresActivity : AppCompatActivity() {
    val addNewStoreFab by lazy { findViewById<View>(R.id.fab) }
    //val recyclerViewStore by lazy { findViewById<RecyclerView>(R.id.recycler_view_store) }
    var firestore:FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)
        addNewStoreFab.setOnClickListener {
            val intent = Intent(this, AddNewStoreActivity::class.java)
            startActivity(intent)
        }
        firestore = FirebaseFirestore.getInstance()
        initStores()
        bindStores()
    }

    private fun initStores() {
        //recyclerViewStore.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindStores() {
        firestore?.collection("arifumutsepetci")?.orderBy("date")?.addSnapshotListener { value, error ->
            val list = arrayListOf<Store>()
            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Store::class.java).also {  store ->
                    store.id = queryDocumentSnapshot.id
                    list.add(store)
                }
            }
            //recyclerViewStore.adapter = StoreAdapter(this, list)
        }
    }
}