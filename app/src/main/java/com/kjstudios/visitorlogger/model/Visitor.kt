package com.kjstudios.visitorlogger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visitors")
data class Visitor(
    val time : String,
    val name: String,
    val contact: String,
    val concernedPerson: String,
    val purposeOfVisit: String,
    val address: String,
    val vehicleNumber: String
) {
    @PrimaryKey( autoGenerate = true) var id = 0
}