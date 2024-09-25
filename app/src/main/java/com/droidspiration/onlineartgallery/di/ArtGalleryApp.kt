package com.droidspiration.onlineartgallery.di

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.droidspiration.onlineartgallery.ui.HomeScreen
import com.droidspiration.onlineartgallery.ui.SearchArtScreen
import com.droidspiration.onlineartgallery.ui.ShowRandomArtScreen
import com.droidspiration.onlineartgallery.ui.theme.OnlineArtGalleryTheme

@Composable
fun ArtGalleryApp() {
    val navController = rememberNavController()

    OnlineArtGalleryTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") { HomeScreen(navController = navController, onCardClick = { navController.navigate("search/${it.name}") }) } //select a museum
            composable(
                route = "search/{museum}",
                arguments = listOf(navArgument("museum") {
                    type = NavType.StringType })
            ) { backStackEntry -> //refresh records for a keyword
                val museum = backStackEntry.arguments?.getString("museum")
                SearchArtScreen(navController = navController, museum = museum)
            }
            composable(
                route = "getArt/{objectId}",
                arguments = listOf(navArgument("objectId") {
                    type = NavType.StringType })
                ) { data ->
                //show random art piece
                val objectId = data.arguments?.getString("objectId")
                ShowRandomArtScreen(objectId = objectId)
            }
        }
    }
}