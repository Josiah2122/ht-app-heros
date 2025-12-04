import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java-library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.ktLint)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {

    implementation(libs.dagger.android)
    ksp(libs.dagger.compiler)

    implementation(libs.androidx.coroutines)
    implementation(libs.resultat)
    implementation(libs.kotlin.extension)
    implementation(libs.bundles.retrofit)
}
