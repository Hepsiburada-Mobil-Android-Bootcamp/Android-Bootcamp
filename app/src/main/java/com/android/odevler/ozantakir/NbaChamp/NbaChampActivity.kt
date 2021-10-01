package com.android.odevler.ozantakir.NbaChamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Question
import com.android.camp.question.QuestionAdapter
import com.android.odevler.ozantakir.data.NbaChamp
import com.google.firebase.firestore.FirebaseFirestore

class NbaChampActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null
    private val recyclerViewNbaChamp by lazy { findViewById<RecyclerView>(R.id.recy_view_nba_champ) }
    private val fabNba by lazy { findViewById<View>(R.id.fab_nba) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nba_champ)

        fabNba.setOnClickListener {
            val intent = Intent(this,AddNbaChampActivity::class.java)
            startActivity(intent)
    }
        initNbaChamp()
        firestore = FirebaseFirestore.getInstance()
        bindNbaChamp()
}
    fun initNbaChamp() {
        recyclerViewNbaChamp.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
    fun bindNbaChamp() {
        firestore?.collection("ozantakir")?.get()?.addOnSuccessListener {
                snapshot ->
            snapshot.toObjects(NbaChamp::class.java)?.let {nbaChamps ->
                recyclerViewNbaChamp.adapter = NbaChampAdapter(nbaChamps as ArrayList<NbaChamp>)
            }
        }

    }



    }
