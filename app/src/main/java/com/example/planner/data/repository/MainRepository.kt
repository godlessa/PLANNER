package com.example.planner.data.repository

import com.example.planner.data.local.database.AppDatabase
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.domain.repository.IMainRepository
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val appDatabase: AppDatabase
) : IMainRepository {

    /*private val errHandler = CoroutineExceptionHandler { _, th ->
        Log.d("M_MainRepository", "scope error ${th.message}")
    }

    private val scope = CoroutineScope(SupervisorJob() + errHandler)*/

    override suspend fun getEvents(): List<EventEntity> =
        appDatabase.eventsDao().getAllEvents()

    override suspend fun getEventToMonth(month: String, year: String): List<EventEntity> =
        appDatabase.eventsDao().getEventsToMonth(
            selectedMonth = month,
            selectedYear = year
        )

    override suspend fun insertEventsList(eventsList: List<EventEntity>) =
        appDatabase.eventsDao().insertEventList(eventsList)

    override suspend fun insertEvent(event: EventEntity) =
        appDatabase.eventsDao().insertEvent(event)

}