package com.hansheung.mob_22_clean.domain.usercase

import android.net.http.HttpException
import com.hansheung.mob_22_clean.core.Resource
import com.hansheung.mob_22_clean.domain.model.Task
import com.hansheung.mob_22_clean.domain.repo.TasksRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTaskUseCase @Inject constructor(
    private val repo: TasksRepo
) {
    operator fun invoke(id:String): Flow<Resource<Task?>> = flow {
        try {
            emit(Resource.Loading())
            val task = repo.getTask(id)
            if(task!= null){
                emit(Resource.Success(task))
            }else{
                emit(Resource.Error("Task not found"))
            }
        }catch (_: Exception){
            emit(Resource.Error("Something went wrong"))
        }
    }
}
