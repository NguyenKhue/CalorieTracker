/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.khue.calorietracker

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose.bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", libs.findLibrary("coil.kt").get())
            add("implementation", libs.findLibrary("coil.compose").get())
            add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
            add("implementation", libs.findLibrary("compose.navigation").get())
            add("implementation", libs.findLibrary("compose.runtime").get())
            add("implementation", libs.findLibrary("viewModel.compose").get())
            add("implementation", libs.findLibrary("material3").get())
            add("implementation", libs.findLibrary("lifecycle.runtime.ktx").get())
            add("implementation", libs.findLibrary("activity.compose").get())
            add("implementation", libs.findLibrary("ui").get())
            add("implementation", libs.findLibrary("ui.graphics").get())
            add("implementation", libs.findLibrary("ui.tooling").get())
            add("implementation", libs.findLibrary("ui.tooling.preview").get())

            add("testImplementation", libs.findLibrary("junit").get())
            add("testImplementation", libs.findLibrary("androidx.test.ext.junit").get())
            add("testImplementation", libs.findLibrary("truth").get())
            add("testImplementation", libs.findLibrary("coroutines.test").get())
            add("testImplementation", libs.findLibrary("turbine").get())
            add("testImplementation", libs.findLibrary("mockk").get())
            add("testImplementation", libs.findLibrary("mockwebserver").get())
            add("testImplementation", libs.findLibrary("ui.test.junit4").get())

            add("androidTestImplementation", libs.findLibrary("junit").get())
            add("androidTestImplementation", libs.findLibrary("androidx.test.ext.junit").get())
            add("androidTestImplementation", libs.findLibrary("truth").get())
            add("androidTestImplementation", libs.findLibrary("coroutines.test").get())
            add("androidTestImplementation", libs.findLibrary("turbine").get())
            add("androidTestImplementation", libs.findLibrary("mockk").get())
            add("androidTestImplementation", libs.findLibrary("mockwebserver").get())
            add("androidTestImplementation", libs.findLibrary("ui.test.junit4").get())
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + buildComposeMetricsParameters()
        }
    }
}

private fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val relativePath = projectDir.relativeTo(rootDir)

    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = rootProject.buildDir.resolve("compose-metrics").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = rootProject.buildDir.resolve("compose-reports").resolve(relativePath)
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    return metricParameters.toList()
}
