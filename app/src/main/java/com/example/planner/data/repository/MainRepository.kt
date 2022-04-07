package com.example.planner.data.repository

import android.util.Log
import com.example.planner.data.local.database.AppDatabase
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.domain.repository.IMainRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val appDatabase: AppDatabase
) : IMainRepository {

    private val errHandler = CoroutineExceptionHandler { _, th ->
        Log.d("PLANNER ERROR", "scope error ${th.message}")
    }

    private val scope = CoroutineScope(SupervisorJob() + errHandler)

    override suspend fun getEvents(): List<EventEntity> =
        appDatabase.eventsDao().getAllEvents()

    override suspend fun getEventToMonth(month: String, year: String): List<EventEntity> =
        appDatabase.eventsDao().getEventsToMonth(
            selectedMonth = month,
            selectedYear = year
        )

    override suspend fun insertEventsList(eventsList: List<EventEntity>) =
        appDatabase.eventsDao().insertEventList(eventsList)

    override fun insertEvent(event: EventEntity) {
        scope.launch(Dispatchers.Default) {
            appDatabase.eventsDao().insertEvent(event)
        }
    }

}