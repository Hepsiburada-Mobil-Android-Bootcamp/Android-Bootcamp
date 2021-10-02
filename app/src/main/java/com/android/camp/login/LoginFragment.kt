package com.android.camp.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.camp.R

class LoginFragment : Fragment() {

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