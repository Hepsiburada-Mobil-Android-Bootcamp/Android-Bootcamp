package com.android.odevler.oguzhanyildirim.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.core.content.ContextCompat
import com.android.camp.R

class StarterActivity : AppCompatActivity() {

    private val btnContinue by lazy { findViewById<Button>(R.id.btnContinue) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)

        btnContinue.setOnClickListener {
            startActivity(Intent(this@StarterActivity, HospitalListActivity::class.java))
        }
    }
}