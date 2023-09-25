import com.khue.calorietracker.CalorieTrackerBuildType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.calorietracker.android.application)
    alias(libs.plugins.calorietracker.android.application.compose)
    alias(libs.plugins.calorietracker.android.application.flavors)
    alias(libs.plugins.calorietracker.android.application.jacoco)
    alias(libs.plugins.calorietracker.android.hilt)
    alias(libs.plugins.calorietracker.android.room)
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

    implementation(projects.core.ui)
    implementation(projects.core.preferences)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.feature.onboarding.onboardingDomain)
    implementation(projects.feature.onboarding.onboardingPresentation)
    implementation(projects.feature.tracker.trackerData)
    implementation(projects.feature.tracker.trackerDomain)
    implementation(projects.feature.tracker.trackerPresentation)

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