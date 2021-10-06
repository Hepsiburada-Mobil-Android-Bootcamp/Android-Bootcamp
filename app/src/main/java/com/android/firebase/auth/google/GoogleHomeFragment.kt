package com.android.firebase.auth.google

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.camp.R
import com.android.camp.databinding.FragmentGoogleHomeBinding
import com.google.firebase.auth.FirebaseAuth

class GoogleHomeFragment : Fragment() {

    private lateinit var binding: FragmentGoogleHomeBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoogleHomeBinding.inflate(layoutInflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        initButtonListener()

        return binding.root
    }

    private fun initButtonListener() {
        binding.btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        firebaseAuth.currentUser ?: (activity as GoogleSignInActivity).initGoogleSignInFragment()
        initData()
    }

    private fun initData() {
        Log.d("Firebase Suc", firebaseAuth.currentUser?.email.toString())
    }

}