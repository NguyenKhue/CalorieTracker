/*
 * Copyright 2022 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import com.android.build.gradle.LibraryExtension
import com.khue.calorietracker.configureGradleManagedDevices
import com.khue.calorietracker.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("calorietracker.android.library")
                apply("calorietracker.android.hilt")
            }
            extensions.configure<LibraryExtension> {
//                defaultConfig {
//                    testInstrumentationRunner =
//                        "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
//                }
                configureGradleManagedDevices(this)
            }

            dependencies {
                add("implementation", project(":core:preferences"))

                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}