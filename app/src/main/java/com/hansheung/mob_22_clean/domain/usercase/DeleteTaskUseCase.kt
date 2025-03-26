package com.hansheung.mob_22_clean.domain.usercase

import com.hansheung.mob_22_clean.core.Resource
import com.hansheung.mob_22_clean.domain.repo.TasksRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repo: TasksRepo
) {
    operator fun invoke(id: String): Flow<Resource<Unit>> = flow{
        try {
            emit(Resource.Loading())
            repo.delete(id)
            emit(Resource.Success(Unit))
        }catch (_: Exception){
            emit(Resource.Error("Something went wrong"))
        }
    }
}