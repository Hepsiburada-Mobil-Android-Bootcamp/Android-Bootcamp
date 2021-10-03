package com.android.camp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.android.camp.R
import com.google.android.material.textfield.TextInputLayout

class SmsVerficationCodeFragment(private val code: (String) -> Unit) : Fragment() {

    private var smsCodeEditText: EditText? = null
    private var smsCodeTextInputLayout: TextInputLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sms_verification_code, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        smsCodeEditText = view.findViewById(R.id.edit_text_sms_code)
        smsCodeTextInputLayout = view.findViewById(R.id.text_input_layout_sms_code)

        view.findViewById<View>(R.id.button_verify).setOnClickListener {
            if (isValid()) {
                code(smsCodeEditText?.text.toString())
            }
        }
    }

    private fun isValid(): Boolean {
        val smsCode = smsCodeEditText?.text.toString()
        if (smsCode.length < 6) {
            smsCodeTextInputLayout?.error = getString(R.string.sms_code_validation)
            smsCodeEditText?.requestFocus()
            return false
        }

        smsCodeTextInputLayout?.error = null
        return true
    }
}