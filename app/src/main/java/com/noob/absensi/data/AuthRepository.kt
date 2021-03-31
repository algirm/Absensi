package com.noob.absensi.data

import com.noob.absensi.data.model.User
import com.noob.absensi.util.Resource
import com.noob.absensi.util.Resources
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(): Resource<String>

    suspend fun signIn(email: String, password: String): Flow<Resource<User>>
}