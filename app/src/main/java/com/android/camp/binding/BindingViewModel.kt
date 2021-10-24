package com.android.camp.binding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BindingViewModel() {

    val sayac:MutableLiveData<Int> by lazy {
        MutableLiveData(0)
    }

    val title:MutableLiveData<String> by lazy {
        MutableLiveData("")
    }

    val result:MutableLiveData<String> by lazy {
        MutableLiveData("")
    }
}