package com.android.firebase.auth.google

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.camp.R
import com.android.camp.databinding.ActivityGoogleSignInBinding
import com.google.firebase.auth.FirebaseUser

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
        binding?.flGoogle?.let { transaction.add(it.id, signInFragment) }
        transaction.commit()
    }

    fun initGoogleHomeFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        binding?.flGoogle?.let {
            transaction.remove(signInFragment)
            transaction.add(it.id,homeFragment)
            transaction.commit()
        }
    }
}