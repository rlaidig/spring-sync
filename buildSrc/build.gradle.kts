plugins {
    alias(libs.plugins.sonarqubeGradle)
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`

    id("com.adarshr.test-logger") version "4.0.0"//https://plugins.gradle.org/plugin/com.adarshr.test-logger
    id("com.github.ben-manes.versions") version "0.52.0" //https://plugins.gradle.org/plugin/com.github.ben-manes.versions
    jacoco
    idea
}
val releaseRepo = "release-repo"
val snapshotRepo = "snapshot-repo"

/*
 * values held in ~/.gradle/gradle.properties
*/
val mavenUser: String by project
val mavenPassword: String by project


repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()

    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven {
            name = releaseRepo
            url = uri("https://nexus.wgu.edu/content/repositories/WGU")
            credentials {
                username = mavenUser
                password = mavenPassword
            }
            mavenContent {
                releasesOnly()
            }
        }
        maven {
            name = snapshotRepo
            url = uri("https://nexus.wgu.edu/content/repositories/WGUSnapshots")
            credentials {
                username = mavenUser
                password = mavenPassword
            }
            mavenContent {
                snapshotsOnly()
            }
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
    implementation("org.sonarsource.scanner.gradle", "sonarqube-gradle-plugin", version = "${libs.versions.sonarqubeGradle.get()}" )
    implementation("org.springframework.boot:spring-boot-gradle-plugin:${libs.versions.springBoot.get()}")

    implementation("com.github.ben-manes:gradle-versions-plugin:${libs.versions.benManesGradle.get()}")
    implementation("me.champeau.gradle:japicmp-gradle-plugin:${libs.versions.japicmp.get()}")
    implementation("org.owasp:dependency-check-gradle:${libs.versions.owaspcvecheck.get()}")

    testImplementation("org.hamcrest:hamcrest-all:${libs.versions.hamcrestVersion.get()}")
    testImplementation("org.easymock:easymock:${libs.versions.easymockVersion.get()}")

}