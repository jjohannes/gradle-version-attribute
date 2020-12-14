plugins {
    `kotlin-dsl`
    id("maven-publish")
}

repositories {
    gradlePluginPortal()
}

group = "org.gradle"
version = "1.0"

publishing {
    repositories {
        maven {
            name = "localPluginRepository"
            url = uri("../local-plugin-repo")
        }
    }
}
