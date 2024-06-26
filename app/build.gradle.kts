@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.dokka) // TODO パッケージ構成についても書くようにしたい https://qiita.com/foxsal/items/6c4a0dfbc8f8e3000077
    alias(libs.plugins.com.google.gms.google.services)
    alias(libs.plugins.com.google.dagger.hilt.android)
}

android {
    namespace = "com.gmail.shu10.dev.app.wants"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.gmail.shu10.dev.app.wants"
        targetSdk = libs.versions.targetSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // Android DSLのパッケージ化オプションのエントリポイント
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    // ----- モジュール依存関係 ----- //
    implementation(project(":feature"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core"))

    // ----- androidx ----- //
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    // ----- test ----- //
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // ----- google ----- //
    // hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)
    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
}