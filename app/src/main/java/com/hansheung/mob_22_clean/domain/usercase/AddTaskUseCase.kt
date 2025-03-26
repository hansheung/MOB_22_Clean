package com.hansheung.mob_22_clean.domain.usercase

import com.hansheung.mob_22_clean.core.Resource
import com.hansheung.mob_22_clean.domain.model.Task
import com.hansheung.mob_22_clean.domain.repo.TasksRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repo: TasksRepo
) {
    operator fun invoke(task: Task): Flow<Resource<Unit>> = flow{
        try {
            require(task.title.isNotEmpty()) {"Title cannot be empty"}
            require(task.desc.isNotEmpty()) {"Description cannot be empty"}
            emit(Resource.Loading())
            repo.addTasks(task)
            emit(Resource.Success(Unit))
        }catch (e: IllegalArgumentException){
            emit(Resource.Error(e.message.toString()))
        }catch (_: Exception){
            emit(Resource.Error("Something went wrong"))
        }
    }
}