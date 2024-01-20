package com.vrushi.emiaggregator.feature_society.presentation.societies

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.vrushi.emiaggregator.feature_society.presentation.SocietyViewModel
import com.vrushi.emiaggregator.feature_society.presentation.societies.components.SocietyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocietyScreen(
    vm: SocietyViewModel = hiltViewModel(),
) {
    SocietyCard("Hello Test")

}