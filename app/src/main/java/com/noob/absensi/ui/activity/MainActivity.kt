package com.noob.absensi.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import com.noob.absensi.R
import com.noob.absensi.viewmodel.LoginViewModel
import com.noob.absensi.viewmodel.TestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}