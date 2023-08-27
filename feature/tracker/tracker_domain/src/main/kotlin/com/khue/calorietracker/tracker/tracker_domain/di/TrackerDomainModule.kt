package com.khue.calorietracker.tracker.tracker_domain.di

import com.khue.calorietracker.core.preferences.domain.preferences.Preferences
import com.khue.calorietracker.tracker.tracker_domain.repository.TrackerRepository
import com.khue.calorietracker.tracker.tracker_domain.use_case.CalculateMealNutrients
import com.khue.calorietracker.tracker.tracker_domain.use_case.DeleteTrackedFood
import com.khue.calorietracker.tracker.tracker_domain.use_case.GetFoodsForDate
import com.khue.calorietracker.tracker.tracker_domain.use_case.SearchFood
import com.khue.calorietracker.tracker.tracker_domain.use_case.TrackFood
import com.khue.calorietracker.tracker.tracker_domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}