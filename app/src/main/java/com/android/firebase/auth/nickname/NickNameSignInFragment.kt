package com.android.firebase.auth.nickname

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.android.camp.databinding.FragmentNickNameSigninBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class NickNameSignInFragment:Fragment() {
    private var _binding: FragmentNickNameSigninBinding? = null
    private val binding get() = _binding!!
    private var firestore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentNickNameSigninBinding.inflate(inflater,container,false)
        val view = binding.root
        binding.buttonNickNameSignIn.setOnClickListener{
            isValid()

        }
        firestore = FirebaseFirestore.getInstance()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun isValid(){
        if(binding.editNickName.isValid()&&binding.editNickNamePassword.isValid()) {
            val user= nickname(
                nickname = binding.editNickName.text.toString(),
                password = binding.editNickNamePassword.text.toString()
            )
            save(user)
        }
    }
    fun save(user:nickname){
        firestore?.collection("serkanozdemirNickname")
            ?.whereEqualTo("nickname","${binding.editNickName.text.toString()}")
            ?.get()?.addOnSuccessListener {
                Log.d("size",it.size().toString())
                if(it.size()==0){
                    firestore?.collection("serkanozdemirNickname")?.add(user)?.addOnSuccessListener {
                        Toast.makeText(activity, "Kayıt basarili", Toast.LENGTH_LONG).show()
                        activity?.supportFragmentManager?.popBackStack()
                    }
                }else
                    Toast.makeText(activity, "Bu kullanici adi alinmis", Toast.LENGTH_LONG).show()
            }
    }



    fun EditText.isValid():Boolean {
        if (text.isNullOrEmpty()) {
            requestFocus()
            error = "bu alan gerekli"
        }
        return text.isNotEmpty()
    }
}