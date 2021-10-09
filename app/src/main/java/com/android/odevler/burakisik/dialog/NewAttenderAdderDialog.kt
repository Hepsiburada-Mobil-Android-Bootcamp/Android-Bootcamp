package com.android.odevler.burakisik.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.camp.R
import com.android.odevler.burakisik.activity.AttendersActivity
import com.android.odevler.burakisik.data.model.Attender
import com.google.firebase.firestore.FirebaseFirestore

class NewAttenderAdderDialog : DialogFragment() {

    private lateinit var myView:View
    private var fireStore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myView =  inflater.inflate(R.layout.burakisik_fragment_dialog_new_attender_adder, container, false)
        return  myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fireStore = FirebaseFirestore.getInstance()
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners(view: View) {
        val etEventName:EditText = myView.findViewById(R.id.etAttenderEventName);
        val etFirstName:EditText = myView.findViewById(R.id.etAttenderFirstName);
        val etLastName:EditText = myView.findViewById(R.id.etAttenderLastName);
        val etEmailAddress:EditText = myView.findViewById(R.id.etAttenderEmailAddress);

        val btnAddNewAttender:Button = myView.findViewById(R.id.btnAddNewAttender);
        btnAddNewAttender.setOnClickListener {
            val attender = Attender(etEventName.text.toString(), etFirstName.text.toString(), etLastName.text.toString(),
            etEmailAddress.text.toString())
            addNewAttender(attender)
        }
    }

    private fun addNewAttender(attender: Attender) {
        fireStore?.collection("event")?.document(attender.eventName)?.collection("attenders")?.add(attender)
            ?.addOnSuccessListener {
                (activity as AttendersActivity?)?.bindAttenders()
                this.dismiss()
            }
            ?.addOnFailureListener {
                Toast.makeText(context, "Some error occurred at the time of adding new attender", Toast.LENGTH_LONG).show()
            }
    }
}