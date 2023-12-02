package com.vrushi.emiaggregator.core.datastore

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val initialStartUp: Boolean = true
)