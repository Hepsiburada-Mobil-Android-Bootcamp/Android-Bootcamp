package com.android.odevler.ozantakir.NbaChamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.question.QuestionAdapter
import com.android.odevler.ozantakir.data.NbaChamp
import com.google.firebase.firestore.FirebaseFirestore

class NbaChampsListActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null
    private val recyclerViewNba by lazy { findViewById<RecyclerView>(R.id.recycler_nba) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nba_champ_list)

        initNbaChamp()
        firestore = FirebaseFirestore.getInstance()
        bindNbaChamp()
    }

    fun initNbaChamp() {
        recyclerViewNba.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    fun bindNbaChamp() {
        firestore?.collection("ozantakir")?.get()?.addOnSuccessListener { snapshot ->
            snapshot.toObjects(NbaChamp::class.java)?.let { nbaChamps ->
                recyclerViewNba.adapter = NbaChampAdapter(nbaChamps as ArrayList<NbaChamp>)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add_champ) {
            startActivity(Intent(this, AddNbaChampActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
