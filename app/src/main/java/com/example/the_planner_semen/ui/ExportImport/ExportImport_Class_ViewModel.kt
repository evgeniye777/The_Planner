package com.example.the_planner_semen.ui.Statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExportImport_Class_ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is ExportImport Fragment"
    }
    val text: LiveData<String> = _text
}