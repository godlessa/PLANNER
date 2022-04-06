package com.example.planner.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planner.data.local.dao.EventsDao
import com.example.planner.data.local.database.AppDatabase.Companion.DATABASE_VERSION
import com.example.planner.data.local.entities.EventEntity

@Database(
    entities = [
        EventEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "com.example.planner.database_planner"
        const val DATABASE_VERSION = 1
    }

    abstract fun eventsDao(): EventsDao
}