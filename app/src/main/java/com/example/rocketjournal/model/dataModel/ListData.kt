package com.example.rocketjournal.model.dataModel

import kotlinx.datetime.LocalDateTime

data class ListData(
    val list_id: Int, // Auto-generated by the database
    val user_id: Int,
    val name: String,
    var is_complete: Boolean,
    var deadline: LocalDateTime?
)