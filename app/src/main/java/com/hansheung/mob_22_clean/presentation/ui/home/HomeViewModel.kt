package com.hansheung.mob_22_clean.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hansheung.mob21firebase.ui.base.BaseViewModel
import com.hansheung.mob_22_clean.core.Resource
import com.hansheung.mob_22_clean.domain.usercase.DeleteTaskUseCase
import com.hansheung.mob_22_clean.domain.usercase.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (

    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase

) : BaseViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init{
        getTasks()
    }

   private fun getTasks() {
        getTasksUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = HomeUiState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomeUiState(tasks = result.data)
                }
                is Resource.Error -> {
                    _error.emit(result.msg)
                }
            }
        }.launchIn(viewModelScope)
   }
}