plugins {
    alias(libs.plugins.calorietracker.android.feature)
    alias(libs.plugins.calorietracker.android.library.jacoco)
    alias(libs.plugins.calorietracker.android.room)
}


android {
    namespace = "com.khue.calorietracker.tracker.tracker_data"
}

dependencies {
    implementation(projects.feature.tracker.trackerDomain)
    implementation(libs.okHttp)
    implementation(libs.okHttp.logging.interceptor)
    implementation(libs.moshi.converter)
    implementation(libs.retrofit)
}