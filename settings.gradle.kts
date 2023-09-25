pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CalorieTracker"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":feature:onboarding:onboarding_presentation")
include(":feature:onboarding:onboarding_domain")
include(":feature:tracker:tracker_data")
include(":feature:tracker:tracker_domain")
include(":feature:tracker:tracker_presentation")
include(":core:preferences")
include(":core:ui")
include(":core:designsystem")
include(":core:data")
include(":core:domain")
include(":core:common")
