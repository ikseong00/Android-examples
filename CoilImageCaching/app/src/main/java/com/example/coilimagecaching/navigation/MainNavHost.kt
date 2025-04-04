package com.example.coilimagecaching.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.request.ImageRequest
import com.example.coilimagecaching.screen.DefaultImageUrlScreen
import com.example.coilimagecaching.screen.ImageUrl.imageUrls
import com.example.coilimagecaching.screen.RemoteImageUrlScreen
import com.example.coilimagecaching.viewmodel.ImageViewModel


@Composable
fun MainNavHost(
    innerPadding: PaddingValues,
    imageLoader: ImageLoader
) {
    val navController = rememberNavController()
    val imageViewModel: ImageViewModel = viewModel()

    // pre-load image urls
    imageUrls.forEach {
        val request = ImageRequest.Builder(LocalContext.current)
            .data(it)
            .build()
        imageLoader.enqueue(request)
    }

    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = Route.RemoteUrl
    ) {
        composable<Route.DefaultUrl> {
            DefaultImageUrlScreen(
                innerPadding = innerPadding,
                imageLoader = imageLoader,
                navigateToRemoteUrl = { navController.navigate(Route.RemoteUrl) }
            )
        }
        composable<Route.RemoteUrl> {
            RemoteImageUrlScreen(
                innerPadding = innerPadding,
                imageLoader = imageLoader,
                navigateToRemoteUrl = { navController.navigate(Route.DefaultUrl) },
                viewModel = imageViewModel
            )
        }
    }
}
