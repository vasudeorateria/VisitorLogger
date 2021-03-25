package com.kjstudios.visitorlogger

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visitors")
class Visitor(
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