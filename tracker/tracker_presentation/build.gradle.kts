plugins {
    id("calorietracker.android.feature.compose")
    id("calorietracker.android.library.jacoco")
}


android {
    namespace = "com.khue.calorietracker.tracker.tracker_presentation"
}

dependencies {
    implementation(project(":tracker:tracker_domain"))
}