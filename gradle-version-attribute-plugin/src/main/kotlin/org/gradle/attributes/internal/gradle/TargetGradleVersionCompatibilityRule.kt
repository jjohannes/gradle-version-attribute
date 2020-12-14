package org.gradle.attributes.internal.gradle

import org.gradle.api.attributes.AttributeCompatibilityRule
import org.gradle.api.attributes.CompatibilityCheckDetails
import org.gradle.util.GradleVersion

class TargetGradleVersionCompatibilityRule : AttributeCompatibilityRule<String> {

    override fun execute(details: CompatibilityCheckDetails<String>) {
        val consumer = details.consumerValue
        val producer = details.producerValue
        if (consumer == null || producer == null) {
            details.compatible()
        } else if (GradleVersion.version(consumer) >= GradleVersion.version(producer)) {
            details.compatible()
        } else {
            details.incompatible()
        }
    }
}