@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("calorietracker.android.feature")
    id("calorietracker.android.library.compose")
    id("calorietracker.android.library.jacoco")
    id("calorietracker.android.room")
}


android {
    namespace = "com.khue.calorietracker.tracker.tracker_data"
}

dependencies {
    implementation(project(":tracker:tracker_domain"))
    implementation(libs.okHttp)
    implementation(libs.okHttp.logging.interceptor)
    implementation(libs.moshi.converter)
    implementation(libs.retrofit)
}