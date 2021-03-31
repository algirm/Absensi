package com.noob.absensi.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.noob.absensi.R
import com.noob.absensi.ui.fragment.login.LoginFragment
import com.noob.absensi.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : BaseActivity() {

//    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, LoginFragment())
            .commit()
    }

}