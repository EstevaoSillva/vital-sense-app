package com.mindsense.core.navigation

sealed class AppDestination(val route: String) {
    data object Onboarding : AppDestination("onboarding")
    data object Login : AppDestination("login")
    data object Dashboard : AppDestination("dashboard")
    data object History : AppDestination("history")
    data object CollectionDetail : AppDestination("collection/{collectionId}") {
        fun createRoute(collectionId: String): String = "collection/$collectionId"
    }
    data object Insights : AppDestination("insights")
    data object Recommendations : AppDestination("recommendations")
    data object Explore : AppDestination("explore")
    data object Article : AppDestination("article/{articleId}") {
        fun createRoute(articleId: String): String = "article/$articleId"
    }
    data object Sync : AppDestination("sync")
    data object Notifications : AppDestination("notifications")
    data object Profile : AppDestination("profile")
    data object Assessment : AppDestination("assessment")
    data object AssessmentResult : AppDestination("assessment-result")
}

enum class BottomDestination(val route: String, val label: String) {
    Dashboard(AppDestination.Dashboard.route, "Início"),
    History(AppDestination.History.route, "Histórico"),
    Insights(AppDestination.Insights.route, "Insights"),
    Explore(AppDestination.Explore.route, "Explorar"),
    Profile(AppDestination.Profile.route, "Perfil"),
}
