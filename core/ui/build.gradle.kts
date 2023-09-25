plugins {
    alias(libs.plugins.calorietracker.android.library)
    alias(libs.plugins.calorietracker.android.library.compose)
    alias(libs.plugins.calorietracker.android.library.jacoco)
    alias(libs.plugins.calorietracker.android.hilt)
}


android {
    namespace = "com.khue.calorietracker.core.ui"
}

dependencies {
    implementation(projects.core.designsystem)
}