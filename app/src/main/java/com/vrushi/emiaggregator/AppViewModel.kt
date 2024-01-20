package com.vrushi.emiaggregator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrushi.emiaggregator.core.datastore.AppSettings
import com.vrushi.emiaggregator.core.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private val _appSettingsState = MutableStateFlow(AppSettings())
    val appSettingsState = _appSettingsState.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreRepository.getAppSettingsFlow()
                .collectLatest {
                    _appSettingsState.value = it
                    _isLoading.value = false
                }
        }
    }

    fun setASInitialStart(value: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.setInitialStart(value)
        }
    }
}