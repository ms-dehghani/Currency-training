pluginManagement {
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

    versionCatalogs {
        create("libs") {
            version("compose", "1.6.2")
            version("compose-activity", "1.8.2")
            version("hilt", "2.48")
            version("hilt-navigation-compose", "1.0.0")
            version("material3", "1.1.2")
            version("core-ktx", "1.12.0")
            version("core-ktx-test", "1.5.0")
            version("appCompat", "1.6.1")
            version("kotlinBom", "1.8.0")
            version("kotlinCoroutines", "1.7.3")
            version("junit-ktx", "1.1.5")
            version("junit", "4.13.2")
            version("test-espresso", "3.5.1")
            version("mockk", "1.13.8")
            version("jupiter", "5.8.2")
            version("test-mockk", "1.13.8")
            version("turbine", "1.0.0")
            version("lifecycle", "1.1.0-alpha03")
            version("retrofit", "2.9.0")
            version("gson", "2.10.1")

            library("compose-ui", "androidx.compose.ui", "ui").versionRef("compose")
            library("compose-ui-tooling", "androidx.compose.ui", "ui-tooling").versionRef("compose")
            library("compose-ui-tooling-preview", "androidx.compose.ui", "ui-tooling-preview").versionRef("compose")
            library("compose-ui-graphics", "androidx.compose.ui", "ui-graphics").versionRef("compose")
            library("compose-ui-test", "androidx.compose.ui", "ui-test-junit4").versionRef("compose")
            library("compose-ui-test-manifest", "androidx.compose.ui", "ui-test-manifest").versionRef("compose")
            library("compose-activity", "androidx.activity", "activity-compose").versionRef("compose-activity")
            bundle("compose", listOf("compose-ui", "compose-ui-tooling", "compose-ui-tooling-preview" , "compose-ui-graphics" , "compose-ui-test",
                "compose-ui-test-manifest","compose-activity"))

            library("hilt", "com.google.dagger", "hilt-android").versionRef("hilt")
            library("hilt-compose", "androidx.hilt", "hilt-navigation-compose").versionRef("hilt-navigation-compose")
            library("hilt-compiler", "com.google.dagger", "hilt-android-compiler").versionRef("hilt")
            bundle("hilt", listOf("hilt","hilt-compose", "hilt-compiler"))

            library("retrofit", "com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            library("retrofit-gson", "com.squareup.retrofit2", "converter-gson").versionRef("retrofit")
            bundle("retrofit", listOf("retrofit", "retrofit-gson"))

            library("material3", "androidx.compose.material3", "material3").versionRef("material3")

            library("core-ktx", "androidx.core", "core-ktx").versionRef("core-ktx")

            library("appcompat", "androidx.appcompat", "appcompat").versionRef("appCompat")

            library("junit", "junit", "junit").versionRef("junit")

            library("kotlin-bom", "org.jetbrains.kotlin", "kotlin-bom").versionRef("kotlinBom")
            library("kotlin-coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").versionRef("kotlinCoroutines")
            bundle("kotlin", listOf("kotlin-bom", "kotlin-coroutines"))

            library("lifecycle", "androidx.lifecycle", "lifecycle-viewmodel-compose").versionRef("lifecycle")


            library("test-coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("kotlinCoroutines")
            library("test-espresso", "androidx.test.espresso", "espresso-core").versionRef("test-espresso")
            library("test-mockk", "io.mockk", "mockk").versionRef("test-mockk")
            library("test-mockk-android", "io.mockk", "mockk-android").versionRef("test-mockk")
            library("test-turbine", "app.cash.turbine", "turbine").versionRef("turbine")
            library("test-hilt", "com.google.dagger", "hilt-android-testing").versionRef("hilt")
            library("test-junit", "androidx.test.ext", "junit-ktx").versionRef("junit-ktx")
            library("test-core-ktx", "androidx.test", "core-ktx").versionRef("core-ktx-test")
            library("test-junit-ktx", "androidx.test.ext", "junit-ktx").versionRef("junit-ktx")
            bundle(
                "test", listOf(
                    "test-hilt",
                    "test-junit",
                    "test-espresso",
                    "test-coroutines",
                    "test-mockk",
                    "test-turbine",
                    "test-core-ktx",
                    "test-junit-ktx"
                )
            )

        }
    }

}

rootProject.name = "Currency"
include(":app")
include(":domain")
include(":data")