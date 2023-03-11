plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlin-android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("feature.notes")
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
    kapt(Plugins.ROOM)

    implementation(project(":core-register"))
    implementation(project(":platform-extensions"))
    implementation(Libraries.AURELIUS)
    implementation(Libraries.COMPOSE_MATERIAL_ICONS_EXTENDED)
    implementation(Libraries.KOIN_ANDROID)
    implementation(Libraries.ROOM)

    androidTestImplementation(project(":feature-notes-test"))
    androidTestImplementation(Libraries.COMPOSE_UI_TEST_JUNIT_4)
    androidTestImplementation(Libraries.COMPOSE_UI_TEST_MANIFEST)
}
