package com.android.camp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    val textView by lazy { findViewById<TextView>(R.id.text_view_name) }
    val buttonQuestions by lazy { findViewById<View>(R.id.button_questions) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonQuestions.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            intent.putExtra("name", "denmee 12345")
            startActivity(intent)
        }

        textView.text = BuildConfig.APPLICATION_ID

        val sayi = "123asas".inteCevir()

        Log.i("CampDenemTest", "onCreate => sayi: $sayi")

        if (sayi % 2 == 0) {
            Log.d("CampDenemTest", "onCreate => çift sayı")
        } else {
            Log.e("CampDenemTest", "onCreate => tek sayı")
        }


    }
}

fun String.inteCevir() = this.toIntOrNull() ?: 0