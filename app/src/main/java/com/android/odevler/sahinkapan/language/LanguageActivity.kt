package com.android.odevler.sahinkapan.language

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.camp.R
import com.android.camp.databinding.ActivityLanguageBinding
import com.android.odevler.mehmet_serkan_guzel.data.Language
import com.android.odevler.mehmet_serkan_guzel.language.AddNewLanguageActivity
import com.google.firebase.firestore.FirebaseFirestore

class LanguageActivity : AppCompatActivity() {


    var firestore: FirebaseFirestore? = null

    var bindingLang:ActivityLanguageBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingLang = DataBindingUtil.setContentView(this, R.layout.activity_language)

        firestore = FirebaseFirestore.getInstance()

        val layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        bindingLang?.languageRecycler?.layoutManager=layoutManager





        bindLanguages()

        bindingLang?.addLanguage?.setOnClickListener {
            val intent= Intent(this,AddNewLanguageActivity::class.java)
            startActivity(intent)
        }





    }

    private fun bindLanguages() {
        firestore?.collection( "languages")?.orderBy("languageCode")?.addSnapshotListener { value, error ->
            val languageList = arrayListOf<Language>()

            value?.forEach { queryDocumentSnapshot ->
                queryDocumentSnapshot.toObject(Language::class.java).also {
                    language -> languageList.add(language)
                }
            }
            bindingLang?.languageRecycler?.adapter=LanguageAdapter(this,languageList)

        }
    }
}