package org.gradle.attributes.internal.gradle

import org.gradle.api.attributes.AttributeDisambiguationRule
import org.gradle.api.attributes.MultipleCandidatesDetails
import org.gradle.util.GradleVersion

class TargetGradleVersionDisambiguationRule : AttributeDisambiguationRule<String> {

    override fun execute(details: MultipleCandidatesDetails<String>) {
        val consumerValue = details.consumerValue
        val consumer = if (consumerValue == null) GradleVersion.current() else GradleVersion.version(consumerValue)
        var bestMatchVersion = GradleVersion.version("0.0")
        var bestMatchAttribute: String? = null

        details.candidateValues.forEach {
            val producer = GradleVersion.version(it)
            if (producer <= consumer && producer > bestMatchVersion) {
                bestMatchVersion = producer
                bestMatchAttribute = it
            }
        }
        if (bestMatchAttribute != null) {
            details.closestMatch(bestMatchAttribute!!)
        }
    }
}