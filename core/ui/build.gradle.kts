plugins {
    id("calorietracker.android.library")
    id("calorietracker.android.library.compose")
    id("calorietracker.android.library.jacoco")
}


android {
    namespace = "com.khue.calorietracker.core.ui"
}

dependencies {
    add("implementation", project(":core:designsystem"))
}