package com.noob.absensi.data

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.noob.absensi.data.model.User
import com.noob.absensi.data.network.AuthApi
import com.noob.absensi.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    @ExperimentalCoroutinesApi
    override suspend fun signIn(email: String, password: String): Flow<Resource<User>> = channelFlow {
        try {
            val firebaseUser = api.firebaseSignIn(email, password).user!!
            val user = getUserData(firebaseUser.uid)!!
            offer(Resource.Success(user))
        } catch (e: Exception) {
            Log.e("AuthRepository", "signIn: ", e)
            val error: Exception = when (e) {
                is FirebaseNetworkException -> {
                    FirebaseNetworkException("Network Error")
                }
                is FirebaseAuthException -> {
                    Exception("Email/Password is invalid")
                }
                is NullPointerException -> {
                    NullPointerException("User not found in server, try to contact administrator")
                }
                else -> e
            }
            offer(Resource.Failure(error))
        }
    }

    private suspend fun getUserData(uid: String): User? {
        return FirebaseFirestore.getInstance()
            .collection("users")
            .document(uid)
            .get()
            .await()
            .toObject(User::class.java)
    }

    override fun login(): Resource<String> {
        Log.d("AuthRepo", "logging in")
        val result = "Logging In - ${sharedPreferences.getString("test", "default")}"
        return Resource.Success(result)
    }

}