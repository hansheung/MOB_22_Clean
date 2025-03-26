package com.hansheung.mob_22_clean.domain.model

data class Task(
    val id: String? = null,
    val title: String = "",
    val desc: String = "",
    val priority: Priority = Priority.LOW,
    val status: Status = Status.PENDING,
    val lastUpdated: Long? = System.currentTimeMillis()
)

enum class Priority{
    LOW, MEDIUM, HIGH
}

enum class Status{
    PENDING, IN_PROGRESS, DONE
}