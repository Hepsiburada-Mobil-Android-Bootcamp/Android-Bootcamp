package com.android.camp.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityBindingExampleBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class BindingExampleActivity : AppCompatActivity() {
    var sayac = 0

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    var lastDS:Long? = 0L
    val model = BindingViewModel()

    val editText by lazy { findViewById<EditText>(R.id.edit_text_title) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBindingExampleBinding>(
            this,
            R.layout.activity_binding_example
        )




        editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val a = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                   val a = ""
            }

            override fun afterTextChanged(p0: Editable?) {
               model.title.value = p0.toString()
            }

        })

        binding.model = sayac

        binding.button.setOnClickListener {
            Log.d("BindingExampleActTitle", "sayacın değeri değişti")
            model.sayac.value = sayac++
        }

        model.sayac.observe(this) {
            Log.d("BindingExampleActTitle", "dinleyici bilgilendiirildi.")

            binding.model = sayac
        }

        model.title.observe(this) { title ->
            binding.result =  when {
               title.length < 5 -> "şifre 5  karakterden küçük olamaz"
                title.length in 5..7 -> "şifre zayıf"
                title.length in 8..10 -> "şifre orta"
                else -> "şifre güçlü"
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("BindingExampleActTitle", "sayac: ${model.sayac.value}")

    }


    fun paging() {
        firestore.collection("aysesenses").orderBy("date").startAfter("date", lastDS).limit(2).get().addOnCompleteListener { task ->

            task.result?.documents?.forEach {
                lastDS = it.getLong("date")
                val title = it.get("title")
                Log.d("BindingExampleActTitle", "title: $title - date: $lastDS")
            }
        }
    }
}