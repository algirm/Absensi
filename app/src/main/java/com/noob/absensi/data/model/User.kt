package com.noob.absensi.data.model

import java.io.Serializable

data class User(
    var name: String,
    var uid: String
) : Serializable {
    constructor() : this("", "")
}
