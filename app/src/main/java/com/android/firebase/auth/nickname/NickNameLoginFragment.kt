package com.android.firebase.auth.nickname

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.camp.R
import com.android.camp.databinding.FragmentNickNameLoginBinding
import com.google.firebase.firestore.FirebaseFirestore

class NickNameLoginFragment : Fragment() {

    private var _binding: FragmentNickNameLoginBinding? = null
    private val binding get() = _binding!!
    private var firestore: FirebaseFirestore? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentNickNameLoginBinding.inflate(inflater,container,false)
        val view = binding.root
        firestore = FirebaseFirestore.getInstance()
        binding.txtSignIn.setOnClickListener{
            openFragment(NickNameSignInFragment())
        }

        binding.buttonNickNameLogin.setOnClickListener{
            isValid()

        }

        return view
    }

    fun isValid(){
        Log.d("sss","${binding.editNickName.text.toString()}  "+"${binding.editNickNamePassword.text.toString()}")
        firestore?.collection("serkanozdemirNickname")
            ?.whereEqualTo("nickname","${binding.editNickName.text.toString()}")
            ?.whereEqualTo("password","${binding.editNickNamePassword.text.toString()}")
            ?.get()?.addOnSuccessListener {
                Log.d("size",it.size().toString())
                if(it.size()==1){
                    Toast.makeText(activity, "Giris yapildi", Toast.LENGTH_LONG).show()
                }else Toast.makeText(activity, "Giris Basarisiz", Toast.LENGTH_LONG).show()
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun openFragment(fragment : Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.nicknameFrame, fragment)?.addToBackStack("")?.commit()
    }
}