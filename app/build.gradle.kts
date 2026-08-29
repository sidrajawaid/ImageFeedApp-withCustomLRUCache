plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    //alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.ksp)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.example.imagefeedapp"
    compileSdk = 37


    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlin:kotlin-stdlib:${libs.versions.kotlin.get()}")
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${libs.versions.kotlin.get()}")
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${libs.versions.kotlin.get()}")
        }
    }

        defaultConfig {
            applicationId = "com.example.imagefeedapp"
            minSdk = 24
            targetSdk = 35
            versionCode = 1
            versionName = "1.0"
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        buildFeatures {
            compose = true
        }
        /* kapt {
        correctErrorTypes= true
    }*/
    }





    dependencies {
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.compose.foundation.layout)
        implementation(libs.androidx.compose.material3)
        implementation(libs.androidx.compose.ui)
        implementation(libs.androidx.compose.ui.graphics)
        implementation(libs.androidx.compose.ui.tooling.preview)
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.composer.shimmer)
        implementation(libs.androidx.compose.material.icons.extended)
        testImplementation(libs.junit)
        //  implementation(libs.androidx.compose)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.compose.ui.test.junit4)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(libs.androidx.junit)
        debugImplementation(libs.androidx.compose.ui.test.manifest)
        debugImplementation(libs.androidx.compose.ui.tooling)
        // staggered grid
        implementation(libs.androidx.compose.foundation)

        testImplementation(libs.mockito.core)
        testImplementation(libs.mockito.kotlin)

//hilt
        implementation(libs.hilt.android)
         ksp(libs.hilt.compiler)


//compose

        implementation(libs.androidx.ui)
        // implementation("androidx.activity:activity-compose:1.8.2")
        implementation(libs.androidx.lifecycle.viewmodel.compose)
        debugImplementation(libs.androidx.ui.tooling)
        implementation(libs.androidx.hilt.navigation.compose)
        implementation(libs.androidx.navigation.compose)

        implementation(libs.retrofit)
        implementation(libs.converter.gson)
        implementation(libs.okhttp)
        implementation(libs.logging.interceptor)

    }
