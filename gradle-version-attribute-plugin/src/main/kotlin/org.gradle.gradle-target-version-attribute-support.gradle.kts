import org.gradle.api.attributes.gradle.TargetGradleVersion
import org.gradle.attributes.internal.gradle.TargetGradleVersionCompatibilityRule
import org.gradle.attributes.internal.gradle.TargetGradleVersionDisambiguationRule
import org.gradle.util.GradleVersion

// We simulate that all Gradle versions 6.0+ request the new attribtue (planned for 7.0) and older versions do not
if (GradleVersion.current() >= GradleVersion.version("6.0")) {
    val desugaredAttribute = Attribute.of("org.gradle.gradle.version", String::class.java)
    subprojects {
        buildscript.dependencies.attributesSchema.attribute(desugaredAttribute) {
            compatibilityRules.add(TargetGradleVersionCompatibilityRule::class)
            disambiguationRules.add(TargetGradleVersionDisambiguationRule::class)
        }
        buildscript.configurations["classpath"].attributes {
            attribute(desugaredAttribute, GradleVersion.current().version)
        }
    }
}

