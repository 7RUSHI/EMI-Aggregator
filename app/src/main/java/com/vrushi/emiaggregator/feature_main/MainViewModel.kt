package com.vrushi.emiaggregator.feature_main

import androidx.lifecycle.ViewModel
import com.vrushi.emiaggregator.core.presentation.AppBarEvents
import com.vrushi.emiaggregator.core.presentation.AppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<UiMainEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _appBarState = MutableStateFlow(AppBarState())
    val appBarState = _appBarState.asStateFlow()
    fun onEvent(event: MainEvents) {
        when (event) {
            else -> {}
        }
    }

    fun onEvent(event: AppBarEvents) {
        when (event) {
            AppBarEvents.ToggleMenuDropdown -> {
                _appBarState.value = appBarState.value.copy(
                    menuExpanded = !appBarState.value.menuExpanded
                )
            }
        }
    }
}