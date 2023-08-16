plugins {
    id("calorietracker.android.feature")
    id("calorietracker.android.library.jacoco")
    id("calorietracker.android.room")
}


android {
    namespace = "com.khue.calorietracker.tracker.tracker_data"
}

dependencies {
    implementation(project(":feature:tracker:tracker_domain"))
    implementation(libs.okHttp)
    implementation(libs.okHttp.logging.interceptor)
    implementation(libs.moshi.converter)
    implementation(libs.retrofit)
}