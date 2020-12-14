package org.gradle.api.attributes.gradle

import org.gradle.api.Named
import org.gradle.api.attributes.Attribute

abstract class TargetGradleVersion : Named {
    companion object {
        /**
         * The minimal target version for a Gradle plugin. A lower Gradle version would not be able to consume it.
         */
        val TARGET_GRADLE_VERSION_ATTRIBUTE: Attribute<TargetGradleVersion> = Attribute.of("org.gradle.gradle.version", TargetGradleVersion::class.java)
    }
}