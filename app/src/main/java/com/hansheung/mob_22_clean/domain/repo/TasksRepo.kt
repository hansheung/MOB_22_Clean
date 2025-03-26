package com.hansheung.mob_22_clean.domain.repo

import com.hansheung.mob_22_clean.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepo {
    fun getAllTasks(): Flow<List<Task>>

    suspend fun getTask(id: String): Task?

    suspend fun addTasks(task: Task)

    suspend fun delete(id: String)
}