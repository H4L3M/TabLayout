package com.example.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.tablayout.FavoriteScreen
import com.example.tablayout.HomeScreen
import com.example.tablayout.SearchScreen


val screens = listOf(
    Screen.Home, Screen.Favorite, Screen.Search
)

typealias Content = @Composable () -> Unit

sealed class Screen(val route: String, val icon: ImageVector, val content: Content) {
    object Home : Screen(route = "Home", icon = Icons.Rounded.Home, content = { HomeScreen() })
    object Favorite :
        Screen(route = "Favorite", icon = Icons.Rounded.Favorite, content = { FavoriteScreen() })

    object Search :
        Screen(route = "Search", icon = Icons.Rounded.Search, content = { SearchScreen() })
}
