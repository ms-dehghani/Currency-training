plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "ir.training.currency.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://developers.paysera.com/\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            resources.excludes.add("META-INF/*")
        }
    }
}

dependencies {

    implementation(project(mapOf("path" to ":domain")))

    implementation(libs.retrofit.gson)
    implementation(libs.retrofit)
    
    implementation(libs.kotlin.coroutines)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(libs.test.mockk)

    testImplementation(libs.junit)
    testImplementation(libs.test.coroutines)

    testImplementation(libs.test.turbine)

}