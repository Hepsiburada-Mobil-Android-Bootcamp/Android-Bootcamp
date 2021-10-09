package com.android.camp.firebase.auth.twitter

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.android.camp.MainActivity
import com.android.camp.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.TwitterAuthProvider

class TwitterLoginFragment : Fragment() {
    private val ref by lazy { FirebaseAuth.getInstance() }
    private var twitter_login_btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_twitter_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        twitter_login_btn = view.findViewById(R.id.twitter_login_btn)

    }

    private fun loginTwitter() {
        val twitterAuthProvider = OAuthProvider.newBuilder("yahoo.com")


        activity?.let {
            ref
                .startActivityForSignInWithProvider( it, twitterAuthProvider.build())
                .addOnSuccessListener(
                    OnSuccessListener<AuthResult?> {
                        Toast.makeText(activity, "Logged in", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(activity, MainActivity::class.java))
                        requireActivity().finish()

                    })
                .addOnFailureListener(
                    OnFailureListener {
                        Toast.makeText(activity, "Can't login", Toast.LENGTH_SHORT).show()

                    })
        }

    }
}