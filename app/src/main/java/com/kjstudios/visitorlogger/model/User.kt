package com.kjstudios.visitorlogger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val password: String,
    val module: String,
    var isLoggedIn:Int
)