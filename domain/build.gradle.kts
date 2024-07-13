plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jlleitschuh.gradle.ktlint)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.androidx.paging.common)
    testImplementation(libs.junit)
}
