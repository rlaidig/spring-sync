import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val javaVersion = "${versionCatalog.findVersion("javaVersion").get()}"

project.logger.debug("javaVersion: $javaVersion")

//configure<JavaPluginExtension> {
//    sourceCompatibility = JavaVersion.toVersion(javaVersion)
//}

//	apply plugin: "propdeps"
//	apply plugin: "propdeps-eclipse"
//	apply plugin: "propdeps-idea"
//	apply plugin: "java"
//	apply plugin: "eclipse"
//	apply plugin: "idea"
//	apply plugin: "javadocHotfix"
plugins {
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
        vendor.set(JvmVendorSpec.AMAZON)
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.valueOf(javaVersion))
    }
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}