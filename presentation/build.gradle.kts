import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktLint)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.hiltApplication)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
    alias(libs.plugins.safe.args.navigation)
}

android {
    namespace = "com.hellotractor.notes.presentation"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
}

dependencies {
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.android)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.utilities)
    implementation(libs.bundles.google)
    implementation(libs.bundles.firebase)
    implementation(libs.bundles.viewModels)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.image.tools)
    implementation(libs.kotlin.extension)
    ksp(libs.hilt.compiler)
}
