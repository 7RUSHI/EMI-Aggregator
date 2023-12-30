package com.vrushi.emiaggregator.feature_society.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SocietyViewModel @Inject constructor(): ViewModel() {
    var expanded by mutableStateOf(false)
        private set
    var appBarTitle by mutableStateOf("Society")
        private set


    fun openDropDown(){
        expanded = true
    }

    fun closeDropDown(){
        expanded = false
    }
}