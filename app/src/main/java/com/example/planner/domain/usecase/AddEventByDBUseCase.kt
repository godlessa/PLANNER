package com.example.planner.domain.usecase

import com.example.planner.data.local.entities.EventEntity
import com.example.planner.domain.repository.IMainRepository
import javax.inject.Inject

class AddEventByDBUseCase @Inject constructor(
    private val mainRepositoryIntf: IMainRepository
) {
    operator fun invoke(event: EventEntity){
        return mainRepositoryIntf.insertEvent(
            event
        )
    }
}