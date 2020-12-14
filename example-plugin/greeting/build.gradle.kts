import org.gradle.api.attributes.gradle.TargetGradleVersion

plugins {
    id("java-gradle-plugin")
    id("org.gradle.gradle-target-version-attribute-support")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.12.0"
}

repositories {
    gradlePluginPortal()
}

group = "com.example"
version = "1.0"

val gradle65 by sourceSets.creating
val gradle67 by sourceSets.creating

java {
    // Add a Gradle 6.5 variant
    registerFeature(gradle65.name) {
        usingSourceSet(gradle65)
        capability(project.group.toString(), project.name, project.version.toString())
        configurations.configureEach {
            if (isCanBeConsumed && name.startsWith(gradle65.name))  {
                attributes {
                    attribute(TargetGradleVersion.TARGET_GRADLE_VERSION_ATTRIBUTE, objects.named("6.5"))
                }
            }
        }
        dependencies {
            "${gradle65.name}CompileOnly"(gradleApiFor("6.5"))
            "${gradle65.name}CompileOnly"(localGroovy())
        }
        tasks.named<Copy>("process${gradle65.name.capitalize()}Resources") {
            val copyPluginDescriptors = rootSpec.addChild()
            copyPluginDescriptors.into("META-INF/gradle-plugins")
            copyPluginDescriptors.from(tasks.pluginDescriptors)
        }
    }

    // Add a Gradle 7.0 variant
    registerFeature(gradle67.name) {
        usingSourceSet(gradle67)
        capability(project.group.toString(), project.name, project.version.toString())
        configurations.configureEach {
            if (isCanBeConsumed && name.startsWith(gradle67.name))  {
                attributes {
                    attribute(TargetGradleVersion.TARGET_GRADLE_VERSION_ATTRIBUTE, objects.named("6.7"))
                }
            }
        }
        dependencies {
            "${gradle67.name}CompileOnly"(gradleApiFor("6.7"))
            "${gradle67.name}CompileOnly"(localGroovy())
        }
        tasks.named<Copy>("process${gradle67.name.capitalize()}Resources") {
            val copyPluginDescriptors = rootSpec.addChild()
            copyPluginDescriptors.into("META-INF/gradle-plugins")
            copyPluginDescriptors.from(tasks.pluginDescriptors)
        }
    }
}

gradlePlugin {
    plugins.create("greeting") {
        id = "com.example.greeting"
        implementationClass = "example.plugin.GreetingPlugin"
    }
}

publishing {
    repositories {
        maven {
            name = "localPluginRepository"
            url = uri("../../local-plugin-repo")
        }
    }
}

fun gradleApiFor(version: String) =
        files("${System.getProperty("user.home")}/.gradle/caches/$version/generated-gradle-jars/gradle-api-$version.jar")
