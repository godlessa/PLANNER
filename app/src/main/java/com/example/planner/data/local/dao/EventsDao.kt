package com.example.planner.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.planner.data.local.entities.EventEntity

@Dao
interface EventsDao {
    @Query(
        """
        SELECT * FROM events
        """
    )
    suspend fun getAllEvents(): List<EventEntity>

    @Query(
        """
            SELECT * FROM events WHERE month = :selectedMonth AND year =:selectedYear
            """
    )
    suspend fun getEventsToMonth(selectedMonth: String, selectedYear: String): List<EventEntity>

    @Insert
    suspend fun insertEventList(eventsList: List<EventEntity>)

    @Insert
    suspend fun insertEvent(event: EventEntity)

    @Delete
    suspend fun deleteThisEvent(event: EventEntity)
}