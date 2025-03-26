package com.hansheung.mob_22_clean.domain.usercase

import android.net.http.HttpException
import com.hansheung.mob_22_clean.core.Resource
import com.hansheung.mob_22_clean.domain.model.Task
import com.hansheung.mob_22_clean.domain.repo.TasksRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repo: TasksRepo
) {
    operator fun invoke(
        sortOrder: SortOrder =SortOrder.ASC
    ): Flow<Resource<List<Task>>> = flow {
        try {
            emit(Resource.Loading())
            repo.getAllTasks().collect{tasks->

                val sortedTasks = when(sortOrder){
                    SortOrder.ASC -> tasks.sortedBy { it.title }
                    SortOrder.DESC -> tasks.sortedByDescending { it.title }
                }

                emit(Resource.Success(sortedTasks))
            }
        }catch (_: Exception){
            emit(Resource.Error("Something went wrong while fetching tasks"))
        }
    }
}

enum class SortOrder{
    ASC, DESC
}