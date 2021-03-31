package com.noob.absensi.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    init {
        Log.d(
            "TestViewModel",
            "String From Shared Preference: ${sharedPreferences.getString("test", "Default")}"
        )
    }

}