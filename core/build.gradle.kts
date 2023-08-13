@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("calorietracker.android.library")
    id("calorietracker.android.library.jacoco")
    id("calorietracker.android.hilt")
}

android {
    namespace = "com.khue.calorietracker.core"
}

dependencies {
}