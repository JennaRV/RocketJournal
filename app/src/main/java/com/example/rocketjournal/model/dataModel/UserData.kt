package com.example.rocketjournal.model.dataModel
data class UserData (
    val user_id: Int? = null,
    val first_name: String = "",
    val last_name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val theme: String = "default",
    val user_auth_id: String,
    val journal_id: Int? = null
)

