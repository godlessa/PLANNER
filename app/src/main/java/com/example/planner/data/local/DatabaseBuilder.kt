package com.example.planner.data.local

import android.arch.persistence.room.Room
import android.content.Context
import com.example.planner.data.local.database.AppDatabase
import com.example.planner.data.local.database.AppDatabase.Companion.DATABASE_NAME

object DatabaseBuilder {
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

}