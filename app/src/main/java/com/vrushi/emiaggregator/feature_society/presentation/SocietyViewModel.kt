package com.vrushi.emiaggregator.feature_society.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SocietyViewModel: ViewModel() {
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