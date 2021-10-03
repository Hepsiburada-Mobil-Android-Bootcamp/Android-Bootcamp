package com.android.camp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.camp.MainActivity
import com.android.camp.R
import com.android.camp.navigationcomponent.BilgiYarismasiActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class LoginActivity : AppCompatActivity() {
    var phoneNumberCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createPhoneNumberCallbacks()

        val loginFragment = LoginFragment(phoneNumberCallbacks)
        openFragment(loginFragment)
    }

    override fun onResume() {
        super.onResume()
        if (auth.currentUser != null) {
            startActivity(Intent(this, BilgiYarismasiActivity::class.java))
            finish()
        }
    }


    private fun createPhoneNumberCallbacks() {
        phoneNumberCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(
                    this@LoginActivity,
                    "Hata alındı. detay: ${p0.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                openSmsCodeVerify(p0, p1)
            }

        }
    }


    fun openSmsCodeVerify(verificationId: String, p1: PhoneAuthProvider.ForceResendingToken) {
        openFragment(SmsVerficationCodeFragment { smsCode ->
            val credential = PhoneAuthProvider.getCredential(verificationId, smsCode)
            auth.signInWithCredential(credential).addOnSuccessListener {
                startActivity(Intent(this, BilgiYarismasiActivity::class.java))
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Giriş Yapılamadı", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun openFragment(fragment: Fragment) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.frame_layout, fragment)
        manager.commitAllowingStateLoss()
    }
}