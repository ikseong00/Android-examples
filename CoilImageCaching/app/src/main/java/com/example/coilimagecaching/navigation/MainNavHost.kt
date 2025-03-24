package com.example.coilimagecaching.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coilimagecaching.screen.DefaultImageUrlScreen


@Composable
fun MainNavHost(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Route.DefaultUrl
    ) {
        composable<Route.DefaultUrl> {
            DefaultImageUrlScreen(
                innerPadding = innerPadding,
                navigateToRemoteUrl = { navController.navigate(Route.RemoteUrl) }
            )
        }
        composable<Route.RemoteUrl> {

        }
    }
}
