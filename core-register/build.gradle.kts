plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Versions.java
    targetCompatibility = Versions.java
}

dependencies {
    implementation(Libraries.KOTLINX_COROUTINES_CORE)

    testImplementation(Libraries.KOTLIN_TEST)
    testImplementation(Libraries.KOTLINX_COROUTINES_TEST)
    testImplementation(Libraries.TURBINE)
}
