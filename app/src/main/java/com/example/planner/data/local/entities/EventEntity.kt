package com.example.planner.data.local.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

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
