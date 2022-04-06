package com.example.planner.domain.repository

import com.example.planner.data.local.entities.EventEntity
import javax.inject.Singleton

interface IMainRepository {

    suspend fun getEvents(): List<EventEntity>

    suspend fun getEventToMonth(month: String, year: String): List<EventEntity>

    suspend fun insertEventsList(eventsList: List<EventEntity>)

    suspend fun insertEvent(event: EventEntity)

}