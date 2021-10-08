package com.android.camp.firebase.auth.email_auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.android.camp.R
import com.google.firebase.auth.FirebaseAuth


class EmailLoginFragment : Fragment() {
    val ref = FirebaseAuth.getInstance()
    var check : Boolean ? = null
    private var emailText : EditText? = null
    private var paswordText : EditText? = null
    private var confirmPaswordText : EditText? = null
    private var signupBttn : TextView? = null
    private var notAMemberYet : View ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailText = view.findViewById(R.id.emailText)
        paswordText = view.findViewById(R.id.passwordText)
        signupBttn = view.findViewById(R.id.loginBttn)
        notAMemberYet = view.findViewById(R.id.member)
        view.findViewById<View>(R.id.member).setOnClickListener {
            openFragment(EmailSignUpFragment())
        }
    } private fun openFragment(fragment: Fragment) {
        activity?.getSupportFragmentManager()?.beginTransaction()
            ?.replace(R.id.frame_layout, fragment, "fragmnetId")
            ?.commit();
//        val manager = supportFragmentManager.beginTransaction()
//        manager.replace(R.id.frame_layout, fragment)
//        manager.commitAllowingStateLoss()
    }


}