plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.ksp)
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.example.imagefeedapp"
    compileSdk = 35


    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.0.21")
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.21")
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
        implementation("androidx.compose.foundation:foundation:1.5.0")

        testImplementation("org.mockito:mockito-core:5.4.0")
        testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")

//hilt
        implementation("com.google.dagger:hilt-android:2.51.1")
        // kapt("com.google.dagger:hilt-compiler:2.51.1")
        ksp("com.google.dagger:hilt-compiler:2.51.1")


//compose

        implementation("androidx.compose.ui:ui")
        // implementation("androidx.activity:activity-compose:1.8.2")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        debugImplementation("androidx.compose.ui:ui-tooling")
        //  implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
        implementation("androidx.navigation:navigation-compose:2.8.0")

        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.retrofit2:converter-gson:2.11.0")
        implementation("com.squareup.okhttp3:okhttp:4.12.0")
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    }
