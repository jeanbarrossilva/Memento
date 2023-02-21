buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Plugins.GRADLE)
        classpath(Plugins.KOTLIN)
    }
}

allprojects {
    repositories {
        aurelius(project)
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
