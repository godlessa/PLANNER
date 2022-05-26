package com.example.planner.presentation.main

import androidx.lifecycle.ViewModel
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.domain.usecase.AddEventByDBUseCase
import com.example.planner.domain.usecase.GetEventsToMonthByDBUseCase
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEventsToMonthByDBUseCase: GetEventsToMonthByDBUseCase,
    private val addEventByDBUseCase: AddEventByDBUseCase
) : ViewModel(
) {

    suspend fun getEventsCurrentMonthFomDB(month: String, year: String): Flow<List<EventEntity>> {
        return getEventsToMonthByDBUseCase(month, year)
    }

    fun addEventInDB(eventNew: EventEntity){
        addEventByDBUseCase(eventNew)
    }




}