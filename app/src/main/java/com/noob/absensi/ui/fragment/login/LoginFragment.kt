package com.noob.absensi.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.noob.absensi.R
import com.noob.absensi.data.AuthRepository
import com.noob.absensi.ui.activity.MainActivity
import com.noob.absensi.ui.fragment.BaseFragment
import com.noob.absensi.util.Resource
import com.noob.absensi.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    @Inject
    lateinit var authRepository: AuthRepository

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login.setOnClickListener {
            progress_bar.show()
            loginViewModel.login(
                    username.text.toString(),
                    password.text.toString()
            )
//            username.setText(authRepository.login())
//            startActivity(Intent(context, MainActivity::class.java))
//            requireActivity().finish()
        }

        lifecycleScope.launchWhenResumed {
            loginViewModel.loginResult.collect { event ->
                when (event) {
                    is Resource.Failure -> {
                        progress_bar.hide()
                        snackLong(event.throwable?.message)
                    }
                    is Resource.Success -> {
                        progress_bar.hide()
                        snackLong(event.data.name)
                    }
                    else -> {}
                }
            }
        }
    }

}