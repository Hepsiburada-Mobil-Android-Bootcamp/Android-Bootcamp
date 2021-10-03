package com.android.camp.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.camp.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginFragment(private val phoneNumberCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks?) : Fragment() {

    var phoneNumberEditText: EditText? = null
    var phoneNumberTextInputLayout: TextInputLayout? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LoginFragmentLC", "onAttach")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        Log.d("LoginFragmentLC", "onCreateView")

        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("LoginFragmentLC", "onStart")

    }

    override fun onStop() {
        super.onStop()
        Log.d("LoginFragmentLC", "onStop")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LoginFragmentLC", "onViewCreated")

        phoneNumberEditText = view.findViewById(R.id.edit_text_phone_number)
        phoneNumberTextInputLayout = view.findViewById(R.id.text_input_layout_phone_number)

        view.findViewById<View>(R.id.button_login).setOnClickListener {
            if (isValid()) {
                login()
            }
        }
    }

    private fun login() {
        val phoneNumber = "+905" + phoneNumberEditText?.text.toString()

        phoneNumberCallbacks?.let { callbacks ->
            val option =    PhoneAuthOptions.newBuilder()
                .setActivity(requireActivity())
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(option)
        }



    }

    private fun isValid(): Boolean {
        val phoneNumber = "5" + phoneNumberEditText?.text.toString()
        if (phoneNumber.length < 10) {
            phoneNumberTextInputLayout?.error = "Telefon numarası 10 karakterden küçük olamaz."
            phoneNumberEditText?.requestFocus()
            return false
        }

        phoneNumberTextInputLayout?.error = null
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginFragmentLC", "onDestroy")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("LoginFragmentLC", "onDestroyView")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("LoginFragmentLC", "onDetach")
    }
}