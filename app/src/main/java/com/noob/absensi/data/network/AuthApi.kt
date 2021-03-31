package com.noob.absensi.data.network

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.noob.absensi.data.model.LoginResponse
import com.noob.absensi.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthApi @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun firebaseSignIn(email: String, password: String): AuthResult {
        Log.d("AuthApi", "Signing In..")
        return firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

}