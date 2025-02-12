plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.detekt)
    alias(libs.plugins.serialization)

}

android {
    namespace = "com.oguzhanozgokce.finishmarmarab2b"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.oguzhanozgokce.finishmarmarab2b"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src/main/assets")
            }
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.analytics.impl)
    implementation(libs.tools.core)
    implementation(libs.benchmark.macro)
    implementation(libs.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.material3)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // OkHttp
    implementation(libs.okhttp3.okhttp)
    implementation(libs.logging.interceptor)

    // Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)

    // Navigation
    implementation(libs.navigation.compose)

    // Serialization
    implementation(libs.serialization.json)

    // Coil
    implementation(libs.coil.compose)

    // Checker
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)

    //Security
    implementation(libs.security.crypto)

    //dataSource
    implementation(libs.datastore.core.android)
    implementation(libs.datastore.preferences)

    // Paging3
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)

    // Splash
    implementation(libs.core.splashscreen)

    // Detekt
    detektPlugins(libs.detekt)

    // SystemUiController
    implementation(libs.accompanist.systemuicontroller)

    //Test
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)

}

detekt {
    config.setFrom(file("$rootDir/detekt/detektConfig.yml"))
    source.from(files("src/main/kotlin"))
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
}
