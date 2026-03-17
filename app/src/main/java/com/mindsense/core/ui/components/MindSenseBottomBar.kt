package com.mindsense.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mindsense.core.navigation.BottomDestination
import com.mindsense.core.designsystem.components.BottomNavItem
import com.mindsense.core.designsystem.components.MindSenseBottomNavigation

@Composable
fun MindSenseBottomBar(navController: NavHostController) {
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = backStackEntry?.destination

    MindSenseBottomNavigation(
        items = BottomDestination.entries.map { destination ->
            BottomNavItem(
                route = destination.route,
                label = destination.label,
                icon = destination.icon,
            )
        },
        selectedRoute = BottomDestination.entries.firstOrNull { destination ->
            currentRoute?.hierarchy?.any { it.route == destination.route } == true
        }?.route,
        onItemSelected = { item ->
            navController.navigate(item.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

internal val BottomDestination.icon: androidx.compose.ui.graphics.vector.ImageVector
    get() = when (this) {
        BottomDestination.Dashboard -> Icons.Rounded.Home
        BottomDestination.History -> Icons.Rounded.History
        BottomDestination.Insights -> Icons.Rounded.BarChart
        BottomDestination.Explore -> Icons.Rounded.Search
        BottomDestination.Profile -> Icons.Rounded.AccountCircle
    }
