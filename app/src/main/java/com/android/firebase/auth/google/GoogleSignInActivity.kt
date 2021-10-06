package com.android.firebase.auth.google

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.camp.databinding.ActivityGoogleSignInBinding

class GoogleSignInActivity : AppCompatActivity() {

    private var binding: ActivityGoogleSignInBinding? = null

    private val signInFragment: GoogleSignInFragment by lazy { GoogleSignInFragment() }
    private val homeFragment: GoogleHomeFragment by lazy { GoogleHomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleSignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initGoogleSignInFragment()
    }

    fun initGoogleSignInFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        binding?.flGoogle?.let {
            transaction.add(it.id, signInFragment)
            transaction.remove(homeFragment)
            transaction.commit()
        }
    }

    fun initGoogleHomeFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        binding?.flGoogle?.let {
            transaction.remove(signInFragment)
            transaction.add(it.id, homeFragment)
            transaction.commit()
        }
    }
}