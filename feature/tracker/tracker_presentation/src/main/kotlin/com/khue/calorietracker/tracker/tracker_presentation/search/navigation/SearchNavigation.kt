package com.khue.calorietracker.tracker.tracker_presentation.search.navigation

import androidx.annotation.VisibleForTesting
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.khue.calorietracker.tracker.tracker_presentation.search.SearchScreenRoute

const val searchRoute = "search_route"

@VisibleForTesting
internal const val mealNameArg = "mealName"

@VisibleForTesting
internal const val dayOfMonthArg = "dayOfMonth"

@VisibleForTesting
internal const val monthArg = "month"

@VisibleForTesting
internal const val yearArg = "year"

fun NavController.navigateToSearch(mealName: String, dayOfMonth: Int, month: Int, year: Int, navOptions: NavOptions? = null) {
    this.navigate("$searchRoute/$mealName/$dayOfMonth/$month/$year", navOptions)
}

fun NavGraphBuilder.searchScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
    onNavigateUp: () -> Unit,
) {
    composable(
        route = "$searchRoute/{$mealNameArg}/{$dayOfMonthArg}/{$monthArg}/{$yearArg}",
        arguments = listOf(
            navArgument(mealNameArg) {
                type = NavType.StringType
            },
            navArgument(dayOfMonthArg) {
                type = NavType.IntType
            },
            navArgument(monthArg) {
                type = NavType.IntType
            },
            navArgument(yearArg) {
                type = NavType.IntType
            },
        )
    ) {
        val mealName = it.arguments?.getString(mealNameArg)!!
        val dayOfMonth = it.arguments?.getInt(dayOfMonthArg)!!
        val month = it.arguments?.getInt(monthArg)!!
        val year = it.arguments?.getInt(yearArg)!!

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