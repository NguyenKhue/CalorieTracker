plugins {
    alias(libs.plugins.calorietracker.android.feature)
    alias(libs.plugins.calorietracker.android.library.jacoco)
}

android {
    namespace = "com.khue.calorietracker.onboarding.onboarding_domain"
}

dependencies {
    implementation(project(":core:common"))
}