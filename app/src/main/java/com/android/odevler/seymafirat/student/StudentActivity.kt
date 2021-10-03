package com.android.odevler.seymafirat.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.camp.R
import com.android.camp.exam.AddNewExamActivity
import com.android.odevler.seymafirat.data.Student
import com.google.firebase.firestore.FirebaseFirestore

class StudentActivity : AppCompatActivity() {
    val recyclerViewStudent by lazy { findViewById<RecyclerView>(R.id.recycler_view_student)}
    val addNewStudentButton by lazy { findViewById<View>(R.id.ekle) }

    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        addNewStudentButton.setOnClickListener {
            val intent = Intent(this, AddNewStudentActivity::class.java)
            startActivity(intent)
        }

        firestore = FirebaseFirestore.getInstance()

        initStudents()
        bindStudents()
    }
    private fun initStudents(){
      recyclerViewStudent.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }
    private fun bindStudents(){
        firestore?.collection("seymafirat")?.addSnapshotListener{value,error ->
            val list = arrayListOf<Student>()

            value?.forEach{queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Student::class.java).also { student ->
                list.add(student)
                //recyclerViewStudent.adapter = StudentAdapter(this, student as ArrayList<Student>)
            }
            }
            recyclerViewStudent.adapter = StudentAdapter(list)
        }
    }
}