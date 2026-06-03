plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.google.ksp.get().pluginId)
}

android {
    namespace = "com.grzeluu.habittracker.source.database"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.koin.android)
    implementation(libs.kotlinx.metadata)
    implementation(libs.timber)
    implementation(libs.kotlinx.datetime)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}