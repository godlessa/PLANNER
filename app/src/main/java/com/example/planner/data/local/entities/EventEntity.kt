package com.example.planner.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(
    tableName = "events",
    indices = [Index(value = ["id"], unique = true)]
)
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var event: String,
    var time: Long = System.currentTimeMillis(),
    var date: String,
    var month : String,
    var year: String,
    var status: String
)
