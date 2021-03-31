package com.noob.absensi.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noob.absensi.data.AuthRepository
import com.noob.absensi.data.model.User
import com.noob.absensi.util.DispatcherProvider
import com.noob.absensi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    var loginString: String = "Not logged in yet"

    private val _loginResult = MutableStateFlow<Resource<User>>(Resource.Empty)
    val loginResult: StateFlow<Resource<User>>
    get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch(dispatchers.io) {
//            when(val loginResponse = authRepository.login()) {
//                is Resource.Failure -> _loginResult.value = LoginState.Failure(loginResponse.throwable)
//                is Resource.Success -> _loginResult.value = LoginState.Success(loginResponse.data)
//            }
            authRepository.signIn(email, password).collect {
                _loginResult.value = it
            }
        }
    }

    sealed class LoginState {
        data class Success(val message: String) : LoginState()
        data class Failure(val throwable: Throwable?) : LoginState()
        object Empty : LoginState()
        object Loading : LoginState()
    }

}