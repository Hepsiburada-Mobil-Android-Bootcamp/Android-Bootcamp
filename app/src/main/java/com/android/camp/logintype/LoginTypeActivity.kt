package com.android.camp.logintype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.camp.R
import com.android.camp.databinding.ActivityLoginTypeBinding

class LoginTypeActivity : AppCompatActivity() {

    private var binding:ActivityLoginTypeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_type)
    }
}