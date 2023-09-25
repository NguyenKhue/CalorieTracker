plugins {
    alias(libs.plugins.calorietracker.android.feature.compose)
    alias(libs.plugins.calorietracker.android.library.jacoco)
}

android {
    namespace = "com.khue.calorietracker.onboarding.onboarding_presentation"
}

dependencies {
    implementation(project(":feature:onboarding:onboarding_domain"))
}