package com.android.camp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.camp.question.QuestionsActivity


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
    }
}

fun String.inteCevir() = this.toIntOrNull() ?: 0