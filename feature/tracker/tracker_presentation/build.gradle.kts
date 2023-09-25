plugins {
    alias(libs.plugins.calorietracker.android.feature.compose)
    alias(libs.plugins.calorietracker.android.library.jacoco)
}


android {
    namespace = "com.khue.calorietracker.tracker.tracker_presentation"
}

dependencies {
    implementation(project(":feature:tracker:tracker_domain"))
}