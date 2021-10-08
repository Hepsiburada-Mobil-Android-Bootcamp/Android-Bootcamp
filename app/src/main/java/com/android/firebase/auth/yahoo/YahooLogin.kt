package com.android.firebase.auth.yahoo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.camp.R
import com.android.camp.databinding.ActivityYahooLoginBinding
import com.android.camp.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth

/* Mehmet Ali Vargün */
class YahooLogin : AppCompatActivity() {

    private lateinit var binding: ActivityYahooLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityYahooLoginBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val loginFragment = YahooAuthFragment()
        openFragment(loginFragment)


    }

    private fun openFragment(fragment: Fragment) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.yahoo_frame, fragment)
        manager.commitAllowingStateLoss()
    }


}