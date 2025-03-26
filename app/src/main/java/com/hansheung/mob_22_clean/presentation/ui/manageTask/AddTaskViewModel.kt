package com.hansheung.mob_22_clean.presentation.ui.manageTask

import androidx.lifecycle.viewModelScope
import com.hansheung.mob21firebase.ui.base.BaseViewModel
import com.hansheung.mob_22_clean.core.Resource
import com.hansheung.mob_22_clean.domain.model.Task
import com.hansheung.mob_22_clean.domain.usercase.AddTaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(

    private val addTaskUseCase: AddTaskUseCase

): BaseViewModel() {

    private val _success = MutableSharedFlow<Unit>()
    val success = _success.asSharedFlow()

    fun addTask(title: String, desc: String){

        addTaskUseCase(Task(title=title, desc=desc)).onEach { result ->
            when(result){
                is Resource.Error -> {
                    _error.emit(result.msg)
                }
                is Resource.Loading -> {
                    //Emit state for loading
                }
                is Resource.Success -> {
                    _success.emit(Unit)
                }
            }
        }.launchIn(viewModelScope)
    }
}