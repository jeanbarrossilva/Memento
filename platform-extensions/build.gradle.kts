plugins {
    id("com.android.library")
    id("kotlin-android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("platform.extensions")
    compileSdk = Versions.Memento.SDK_COMPILE

    defaultConfig {
        minSdk = Versions.Memento.SDK_MIN

        @Suppress("Deprecation")
        targetSdk = Versions.Memento.SDK_TARGET

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    kotlinOptions {
        jvmTarget = Versions.java.toString()
    }
}

dependencies {
    implementation(Libraries.CORE)
    implementation(Libraries.KOTLINX_COROUTINES_CORE)
    implementation(Libraries.LIFECYCLE_VIEWMODEL)

    testImplementation(Libraries.JUNIT)
    testImplementation(Libraries.KOTLINX_COROUTINES_TEST)
    testImplementation(Libraries.TURBINE)
}
