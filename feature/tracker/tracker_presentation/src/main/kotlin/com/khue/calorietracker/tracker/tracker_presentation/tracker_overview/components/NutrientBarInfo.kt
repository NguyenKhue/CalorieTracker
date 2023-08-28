package com.khue.calorietracker.tracker.tracker_presentation.tracker_overview.components

import android.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NutrientBarInfo(
    value: Int,
    goal: Int,
    name: Int,
    color: Color,
    modifier: Modifier = Modifier,
    stokeWidth: Dp = 8.dp
) {
    val background = MaterialTheme.colorScheme.background
    val goalExceededColor = MaterialTheme.colorScheme.error
}