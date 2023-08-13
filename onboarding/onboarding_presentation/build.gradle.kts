@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("calorietracker.android.feature")
    id("calorietracker.android.library.compose")
    id("calorietracker.android.library.jacoco")
}

android {
    namespace = "com.khue.calorietracker.onboarding.onboarding_presenstation"
}

dependencies {
    implementation(project(":onboarding:onboarding_domain"))
}