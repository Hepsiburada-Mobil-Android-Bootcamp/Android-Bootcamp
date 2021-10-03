package com.android.odevler.tahaarican.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.data.model.Exam
import com.android.camp.exam.ExamAdapter
import com.android.odevler.tahaarican.data.User
import com.google.firebase.firestore.FirebaseFirestore

class UserActivity : AppCompatActivity() {
    val addUserFab by lazy { findViewById<View>(R.id.add_user_fab) }
    val userRecyclerView by lazy { findViewById<RecyclerView>(R.id.user_recycler_view) }
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        addUserFab.setOnClickListener {
            val intent = Intent(this, AddNewUserActivity::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()

        initUsers()
        bindUsers()
    }

    private fun initUsers() {
        userRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun bindUsers() {
        firestore?.collection("tahaarican")?.orderBy("createdDate")?.addSnapshotListener { value, error ->
            val list = arrayListOf<User>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(User::class.java).also { user ->
                    user.id = queryDocumentSnapshot.id
                    list.add(user)
                }
            }

            userRecyclerView.adapter = UserAdapter(list)
        }
    }
}