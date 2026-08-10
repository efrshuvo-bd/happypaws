package com.auramislab.happypaws.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.auramislab.happypaws.ui.foundation.ArchitectureHomeRoute

/**
 * Single-activity navigation host placeholder.
 * Auth vs main graph gating is deferred until session/auth Tasks.
 */
@Composable
fun HappyPawsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier,
    ) {
        composable(Routes.HOME) {
            ArchitectureHomeRoute()
        }
    }
}
