package com.vrushi.emiaggregator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrushi.emiaggregator.feature_onboard.presentation.OnboardEvents
import com.vrushi.emiaggregator.feature_onboard.presentation.OnboardState
import com.vrushi.emiaggregator.feature_onboard.presentation.PermissionsScreenEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(OnboardState())
    val state = _state.asStateFlow()
    private val _sharedFlow = MutableSharedFlow<PermissionsScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()
    fun onEvent(event: OnboardEvents) {
        when (event) {
            is OnboardEvents.OnPermissionResult -> {
                if (!event.isGranted) {
                    viewModelScope.launch {
                        _sharedFlow.emit(PermissionsScreenEvents.ShowSnackbar(event.permission, R.string.app_permissions_snackbar_message))
                    }
                }
            }

            is OnboardEvents.OnAllGrant -> {
                _state.value = state.value.copy(
                    isAllPermissionsAccepted = true
                )
            }

        }
    }
}