plugins {
    id("com.android.application")
    id("kotlin-android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.NAMESPACE
    compileSdk = Versions.Memento.SDK_COMPILE

    defaultConfig {
        applicationId = Metadata.NAMESPACE
        minSdk = Versions.Memento.SDK_MIN
        targetSdk = Versions.Memento.SDK_TARGET
        versionCode = Versions.Memento.CODE
        versionName = Versions.Memento.NAME
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
    implementation(project(":aurelius"))
    implementation(project(":core-register"))
    implementation(project(":feature-editor"))
    implementation(project(":feature-notes"))
    implementation(project(":platform-register"))
    implementation(Libraries.KOIN_ANDROID)
}
