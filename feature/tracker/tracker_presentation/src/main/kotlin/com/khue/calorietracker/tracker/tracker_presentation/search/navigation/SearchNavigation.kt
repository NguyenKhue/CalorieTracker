package com.khue.calorietracker.tracker.tracker_presentation.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.khue.calorietracker.tracker.tracker_presentation.search.SearchScreenRoute

const val searchRoute = "search_route"

fun NavController.navigateToSearch(mealName: String, dayOfMonth: Int, month: Int, year: Int, navOptions: NavOptions? = null) {
    this.navigate("$searchRoute/$mealName/$dayOfMonth/$month/$year", navOptions)
}

fun NavGraphBuilder.searchScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    onNavigateUp: () -> Unit,
) {
    composable(
        route = "$searchRoute/{mealName}/{dayOfMonth}/{month}/{year}",
        arguments = listOf(
            navArgument("mealName") {
                type = NavType.StringType
            },
            navArgument("dayOfMonth") {
                type = NavType.IntType
            },
            navArgument("month") {
                type = NavType.IntType
            },
            navArgument("year") {
                type = NavType.IntType
            },
        )
    ) {
        val mealName = it.arguments?.getString("mealName")!!
        val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
        val month = it.arguments?.getInt("month")!!
        val year = it.arguments?.getInt("year")!!

        SearchScreenRoute(
            mealName = mealName,
            dayOfMonth = dayOfMonth,
            month = month,
            year = year,
            onNavigateUp = onNavigateUp,
            onShowSnackbar = onShowSnackbar
        )
    }
}