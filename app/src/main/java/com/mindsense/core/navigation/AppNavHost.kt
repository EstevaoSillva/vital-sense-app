package com.mindsense.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mindsense.feature.assessment.AssessmentResultPlaceholderScreen
import com.mindsense.feature.assessment.AssessmentScreen
import com.mindsense.feature.auth.LoginScreen
import com.mindsense.feature.content.ArticlePlaceholderScreen
import com.mindsense.feature.content.ExploreScreen
import com.mindsense.feature.dashboard.DashboardScreen
import com.mindsense.feature.history.CollectionDetailPlaceholderScreen
import com.mindsense.feature.history.HistoryScreen
import com.mindsense.feature.insights.InsightsScreen
import com.mindsense.feature.insights.RecommendationsPlaceholderScreen
import com.mindsense.feature.notifications.NotificationsScreen
import com.mindsense.feature.onboarding.OnboardingScreen
import com.mindsense.feature.profile.ProfileScreen
import com.mindsense.feature.sync.SyncScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Onboarding.route,
        modifier = modifier,
    ) {
        composable(AppDestination.Onboarding.route) {
            OnboardingScreen(
                onContinue = { navController.navigate(AppDestination.Login.route) },
            )
        }
        composable(AppDestination.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppDestination.Dashboard.route) {
                        popUpTo(AppDestination.Onboarding.route) { inclusive = true }
                    }
                },
            )
        }
        composable(AppDestination.Dashboard.route) {
            DashboardScreen(
                navController = navController,
                onOpenCollection = {
                    navController.navigate(AppDestination.CollectionDetail.createRoute("1"))
                },
                onOpenInsights = { navController.navigate(AppDestination.Insights.route) },
                onOpenNotifications = { navController.navigate(AppDestination.Notifications.route) },
                onOpenSync = { navController.navigate(AppDestination.Sync.route) },
                onOpenAssessment = { navController.navigate(AppDestination.Assessment.route) },
            )
        }
        composable(AppDestination.History.route) {
            HistoryScreen(
                navController = navController,
                onOpenCollection = {
                    navController.navigate(AppDestination.CollectionDetail.createRoute("1"))
                },
            )
        }
        composable(
            route = AppDestination.CollectionDetail.route,
            arguments = listOf(navArgument("collectionId") { type = NavType.StringType }),
        ) {
            CollectionDetailPlaceholderScreen(
                navController = navController,
                onBack = { navController.popBackStack() },
            )
        }
        composable(AppDestination.Insights.route) {
            InsightsScreen(
                navController = navController,
                onOpenRecommendations = { navController.navigate(AppDestination.Recommendations.route) },
            )
        }
        composable(AppDestination.Recommendations.route) {
            RecommendationsPlaceholderScreen(
                navController = navController,
                onBack = { navController.popBackStack() },
            )
        }
        composable(AppDestination.Explore.route) {
            ExploreScreen(
                navController = navController,
                onOpenArticle = { navController.navigate(AppDestination.Article.createRoute("1")) },
            )
        }
        composable(
            route = AppDestination.Article.route,
            arguments = listOf(navArgument("articleId") { type = NavType.StringType }),
        ) {
            ArticlePlaceholderScreen(
                navController = navController,
                onBack = { navController.popBackStack() },
            )
        }
        composable(AppDestination.Sync.route) {
            SyncScreen(
                navController = navController,
                onBack = { navController.popBackStack() },
            )
        }
        composable(AppDestination.Notifications.route) {
            NotificationsScreen(
                navController = navController,
                onBack = { navController.popBackStack() },
            )
        }
        composable(AppDestination.Profile.route) {
            ProfileScreen(
                navController = navController,
                onLogout = {
                    navController.navigate(AppDestination.Onboarding.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                },
                onOpenSync = { navController.navigate(AppDestination.Sync.route) },
            )
        }
        composable(AppDestination.Assessment.route) {
            AssessmentScreen(
                navController = navController,
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigate(AppDestination.AssessmentResult.route) },
            )
        }
        composable(AppDestination.AssessmentResult.route) {
            AssessmentResultPlaceholderScreen(
                navController = navController,
                onOpenInsights = { navController.navigate(AppDestination.Insights.route) },
                onBackToDashboard = {
                    navController.navigate(AppDestination.Dashboard.route) {
                        popUpTo(AppDestination.Dashboard.route) { inclusive = false }
                    }
                },
            )
        }
    }
}
