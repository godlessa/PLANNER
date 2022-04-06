package com.example.planner.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner.data.local.entities.EventEntity
import com.example.planner.data.repository.MainRepository
import com.example.planner.domain.usecase.GetEventsToMonthByDBUseCase
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEventsToMonthByDBUseCase: GetEventsToMonthByDBUseCase
) : ViewModel(
) {
    var events = MutableStateFlow(listOf<EventEntity>())


    /*private fun getEventsFomDB(){
        viewModelScope.launch {
            try {
                val eventsFromDb = repository.getEvents()
            } catch (e: Exception){
                Log.d("PLANNER ERROR", e.message ?: "" + " " + e.localizedMessage)
            }
        }
    }*/

    fun getEventsCurrentMonthFomDB(month: String, year: String): Flow<List<EventEntity>> {
        return getEventsCurrentMonthFomDB(month, year)
    }
}