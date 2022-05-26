package com.example.planner.domain.usecase

import com.example.planner.data.local.entities.EventEntity
import com.example.planner.domain.repository.IMainRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetEventsToMonthByDBUseCase @Inject constructor(
    private val mainRepository: IMainRepository
) {
    suspend operator fun invoke(selectedMonth: String, selectedYear: String): Flow<List<EventEntity>> {
        return mainRepository.getEventToMonthYear(
            month = selectedMonth,
            year = selectedYear
        )
    }
}