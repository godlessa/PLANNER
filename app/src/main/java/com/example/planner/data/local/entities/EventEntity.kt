package com.example.planner.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "events",
    indices = [Index(value = ["id"], unique = true)]
)
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var event: String,
    var time: String,
    var date: String,
    var month : String,
    var year: String,
    var status: String
)
