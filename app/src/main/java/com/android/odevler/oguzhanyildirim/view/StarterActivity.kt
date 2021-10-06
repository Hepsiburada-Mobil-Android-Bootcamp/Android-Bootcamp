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
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityStarterBinding

class StarterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val starterBinding = DataBindingUtil.setContentView<ActivityStarterBinding>(
            this,
            R.layout.activity_starter
        )

        starterBinding.btnContinue.setOnClickListener {
            startActivity(Intent(this@StarterActivity, HospitalListActivity::class.java))
        }
    }
}