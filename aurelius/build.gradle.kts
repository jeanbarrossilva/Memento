plugins {
    id("com.android.library")
    id("kotlin-android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("aurelius")
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
        viewBinding = true
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
    api(Libraries.COMPOSE_MATERIAL_3)
    api(Libraries.COMPOSE_UI_TOOLING)
    api(Libraries.CUSTOMVIEW)
    api(Libraries.CUSTOMVIEW_POOLINGCONTAINER)
    api(Libraries.FRAGMENT)
    api(Libraries.LIFECYCLE_VIEWMODEL_SAVEDSTATE)
    api(Libraries.MATERIAL)

    implementation(Libraries.ACCOMPANIST_PAGER)
    implementation(Libraries.ACCOMPANIST_PLACEHOLDER_MATERIAL)
    implementation(Libraries.ACCOMPANIST_SYSTEMUICONTROLLER)
    implementation(Libraries.APPCOMPAT)
    implementation(Libraries.CONSTRAINTLAYOUT_COMPOSE)
    implementation(Libraries.COMPOSE_MATERIAL)

    androidTestImplementation(Libraries.COMPOSE_UI_TEST_JUNIT_4)
    androidTestImplementation(Libraries.COMPOSE_UI_TEST_MANIFEST)
    androidTestImplementation(Libraries.TEST_RUNNER)
}
