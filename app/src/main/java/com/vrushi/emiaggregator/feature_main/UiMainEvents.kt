package com.vrushi.emiaggregator.feature_main

import com.vrushi.emiaggregator.core.presentation.util.UiText

sealed class UiMainEvents {
    data class ShowSnackbar(val message: UiText) : UiMainEvents()
}
