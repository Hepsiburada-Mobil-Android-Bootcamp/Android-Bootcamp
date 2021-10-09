package com.android.camp.firebase.auth.twitter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.camp.R


class TwitterLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_login)

        val twitterFragment = TwitterLoginFragment()
        openFragment(twitterFragment)


    }

    private fun openFragment(fragment: Fragment) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.twitterFrameLayout, fragment)
        manager.commitAllowingStateLoss()
    }



}