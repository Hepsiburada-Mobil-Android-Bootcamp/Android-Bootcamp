package com.android.firebase.auth.yahoo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.camp.MainActivity
import com.android.camp.databinding.FragmentYahooAuthBinding
import com.android.camp.navigationcomponent.BilgiYarismasiActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class YahooAuthFragment : Fragment() {
    private var _binding: FragmentYahooAuthBinding? = null
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYahooAuthBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textButton.setOnClickListener {
            loginYahoo()
        }


    }
    private fun loginYahoo() {
        val provider = OAuthProvider.newBuilder("yahoo.com")

        activity?.let {
            auth
                .startActivityForSignInWithProvider( /* activity= */it, provider.build())
                .addOnSuccessListener(
                    OnSuccessListener<AuthResult?> {
                        Toast.makeText(activity, "Logged in", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(activity, MainActivity::class.java))
                        requireActivity().finish()

                    })
                .addOnFailureListener(
                    OnFailureListener {
                        Toast.makeText(activity, "Failed Please Try Again", Toast.LENGTH_SHORT).show()
                    })
        }

        }


    }



