package com.example.the_planner_semen.ui.Coming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Coming_Class_ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Coming Fragment"
    }
    val text: LiveData<String> = _text
}