plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlin-android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("feature.notes.test")
    compileSdk = Versions.Memento.SDK_COMPILE

    defaultConfig {
        minSdk = Versions.Memento.SDK_MIN

        @Suppress("DEPRECATION")
        targetSdk = Versions.Memento.SDK_TARGET

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    kotlinOptions {
        jvmTarget = Versions.java.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
}

dependencies {
    implementation(project(":feature-notes"))
    implementation(Libraries.COMPOSE_UI_TEST_JUNIT_4)
}
