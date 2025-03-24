package com.example.coilimagecaching.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object DefaultUrl : Route()
    @Serializable
    data object RemoteUrl : Route()
}