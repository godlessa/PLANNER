package com.example.planner.domain.usecase

import com.example.planner.data.local.entities.EventEntity
import com.example.planner.domain.repository.IMainRepository
import javax.inject.Inject

class GetEventsToMonthByDBUseCase @Inject constructor(
    private val mainRepository: IMainRepository
) {
    suspend operator fun invoke(selectedMonth: String, selectedYear: String): List<EventEntity> {
        return mainRepository.getEventToMonth(
            month = selectedMonth,
            year = selectedYear
        )
    }
}