package com.android.firebase.auth.nickname

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.android.camp.R

class NicknameLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname_login)
        val nickNameLoginFragment = NickNameLoginFragment()
        val fm: FragmentManager = supportFragmentManager
        fm.beginTransaction().add(R.id.nicknameFrame, nickNameLoginFragment).commit()
    }

}