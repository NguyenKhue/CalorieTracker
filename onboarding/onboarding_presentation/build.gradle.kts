plugins {
    id("calorietracker.android.feature.compose")
    id("calorietracker.android.library.jacoco")
}

android {
    namespace = "com.khue.calorietracker.onboarding.onboarding_presentation"
}

dependencies {
    implementation(project(":onboarding:onboarding_domain"))
}