package com.android.camp.firebase.auth.email_auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.android.camp.R
import com.android.camp.login.LoginFragment
import com.android.camp.navigationcomponent.BilgiYarismasiActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class EmailSignUpFragment : Fragment() {
    val ref = FirebaseAuth.getInstance()
    var check : Boolean ? = null
    private var emailText : EditText ? = null
    private var paswordText : EditText ? = null
    private var confirmPaswordText : EditText ? = null
    private var signupBttn : TextView ? = null
    private var alreadyHaveAnAccount : View ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailText = view.findViewById(R.id.emailText)
        paswordText = view.findViewById(R.id.passwordText)
        confirmPaswordText = view.findViewById(R.id.confirmPasswordText)
        signupBttn = view.findViewById(R.id.signupBttn)
        alreadyHaveAnAccount = view.findViewById(R.id.account)
        view.findViewById<View>(R.id.account).setOnClickListener {
            openFragment(EmailLoginFragment())
        }
        view.findViewById<View>(R.id.signupBttn).setOnClickListener {
          check =  checkPassword(paswordText,confirmPaswordText,view)
            println("şifre eşleşti!")

            if (check as Boolean){
                signupUser(emailText?.text.toString(), paswordText?.text.toString(),view)
            }else{
                onSNACK(view,"Password and Confirm Password must be same!")
            }


        }


    }
    private fun signupUser(email:String, password:String,view: View){

            ref.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                println("kayit basarili")
                onSNACK(view,"Great! Welcome the competition")
                startActivity(Intent(context, BilgiYarismasiActivity::class.java))

               // openFragment(EmailLoginFragment())

            }.addOnFailureListener {
               onSNACK(view,"Please check your mail!")

            }


    }
    private fun onSNACK(view: View, text:String){
        val snack = Snackbar.make(view,text,Snackbar.LENGTH_LONG)

        snack.show()

    }
    private fun checkPassword(text1: EditText?, text2:EditText?, view: View): Boolean {
        return text1?.text.toString() == text2?.text.toString()
    }
    private fun openFragment(fragment: Fragment) {
        activity?.getSupportFragmentManager()?.beginTransaction()
            ?.replace(R.id.frame_layout, fragment, "fragmnetId")
            ?.commit();
//        val manager = supportFragmentManager.beginTransaction()
//        manager.replace(R.id.frame_layout, fragment)
//        manager.commitAllowingStateLoss()
    }


}