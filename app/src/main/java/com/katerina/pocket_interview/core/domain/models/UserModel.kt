package com.katerina.pocket_interview.core.domain.models

data class UserModel (
    var id: String = "",
    var fullname: String = "",
    var email: String = "",
    var collections: Map<String, String>? = null
)