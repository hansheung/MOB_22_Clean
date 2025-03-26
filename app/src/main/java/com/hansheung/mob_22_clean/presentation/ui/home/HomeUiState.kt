package com.hansheung.mob_22_clean.presentation.ui.home

import com.hansheung.mob_22_clean.domain.model.Task

data class HomeUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
)
