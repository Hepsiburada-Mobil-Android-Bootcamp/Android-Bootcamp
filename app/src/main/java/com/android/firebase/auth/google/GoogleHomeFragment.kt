package com.android.firebase.auth.google

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.camp.databinding.FragmentGoogleHomeBinding
import com.android.firebase.auth.google.model.User
import com.google.firebase.auth.FirebaseAuth

class GoogleHomeFragment : Fragment() {

    private var binding: FragmentGoogleHomeBinding? = null

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoogleHomeBinding.inflate(layoutInflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        initButtonListener()

        return binding?.root
    }

    private fun initButtonListener() {
        binding?.btnSignOut?.setOnClickListener {
            firebaseAuth?.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        firebaseAuth?.currentUser ?: (activity as GoogleSignInActivity).initGoogleSignInFragment()
        initData()
    }

    private fun initData() {
        binding?.user = User(
            firebaseAuth?.currentUser?.displayName.toString(),
            firebaseAuth?.currentUser?.email.toString(),
            firebaseAuth?.currentUser?.phoneNumber.toString(),
            firebaseAuth?.currentUser?.photoUrl.toString()
        )
    }

}