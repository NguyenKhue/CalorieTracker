plugins {
    id("calorietracker.android.feature.compose")
    id("calorietracker.android.library.jacoco")
}


android {
    namespace = "com.khue.calorietracker.tracker.tracker_presentation"
}

dependencies {
    implementation(project(":feature:tracker:tracker_domain"))
}