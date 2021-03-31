package com.noob.absensi.data.model

import com.google.firebase.auth.FirebaseUser

data class LoginResponse(
    var isLoggedIn: Boolean = false,
    var user: FirebaseUser? = null
) {
}