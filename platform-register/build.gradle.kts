plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlin-android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("platform.register")
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

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    kotlinOptions {
        jvmTarget = Versions.java.toString()
    }
}

dependencies {
    kapt(Plugins.ROOM)

    implementation(project(":core-register"))
    implementation(Libraries.KOIN_ANDROID)
    implementation(Libraries.ROOM)

    androidTestImplementation(project(":platform-register-test"))
    androidTestImplementation(Libraries.KOTLINX_COROUTINES_TEST)
    androidTestImplementation(Libraries.TEST_CORE)
    androidTestImplementation(Libraries.TEST_RUNNER)
    androidTestImplementation(Libraries.TURBINE)
}
