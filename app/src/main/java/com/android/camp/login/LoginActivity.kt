package com.android.camp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.camp.R
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginFragment = LoginFragment()
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.frame_layout, loginFragment)

        manager.commitAllowingStateLoss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val loginFragment = LoginFragment()
        loginFragment.onActivityResult(requestCode, resultCode, data)
    }
}