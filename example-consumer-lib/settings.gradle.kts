pluginManagement {
    repositories {
        maven { url = uri("../local-plugin-repo") }
        gradlePluginPortal()
    }
}

includeBuild("../gradle-version-attribute-plugin")

include("lib")