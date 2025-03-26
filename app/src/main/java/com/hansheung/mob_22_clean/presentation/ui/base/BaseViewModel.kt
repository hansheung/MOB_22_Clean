package com.hansheung.mob21firebase.ui.base

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel: ViewModel() {

    protected val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

}