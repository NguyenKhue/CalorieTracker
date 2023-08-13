import com.khue.calorietracker.CalorieTrackerBuildType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("calorietracker.android.application")
    id("calorietracker.android.application.compose")
    id("calorietracker.android.application.flavors")
    id("calorietracker.android.application.jacoco")
    id("calorietracker.android.hilt")
    id("calorietracker.android.room")
    id("jacoco")
}

android {
    namespace = "com.khue.calorietracker"

    defaultConfig {
        applicationId = "com.khue.calorietracker"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        debug {
            applicationIdSuffix = CalorieTrackerBuildType.DEBUG.applicationIdSuffix
        }

        val release by getting {
            isMinifyEnabled = true
            applicationIdSuffix = CalorieTrackerBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":onboarding:onboarding_domain"))
    implementation(project(":onboarding:onboarding_presentation"))
    implementation(project(":tracker:tracker_data"))
    implementation(project(":tracker:tracker_domain"))
    implementation(project(":tracker:tracker_presentation"))

    implementation(libs.appcompat)
    implementation(libs.okHttp)
    implementation(libs.retrofit)
    implementation(libs.okHttp.logging.interceptor)
    implementation(libs.moshi.converter)

    implementation(libs.core.ktx)

    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.runner)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}