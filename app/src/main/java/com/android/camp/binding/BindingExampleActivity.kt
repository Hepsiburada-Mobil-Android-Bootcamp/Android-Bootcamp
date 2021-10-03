package com.android.camp.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityBindingExampleBinding

class BindingExampleActivity : AppCompatActivity() {
    var sayac = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBindingExampleBinding>(
            this,
            R.layout.activity_binding_example
        )

        binding.model = sayac

        binding.button.setOnClickListener {
            binding.model = sayac++
        }
    }
}