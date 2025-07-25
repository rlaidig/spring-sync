plugins {
    alias(libs.plugins.sonarqubeGradle)
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
//    id("org.sonarqube") version "6.2.0.5505" // https://plugins.gradle.org/plugin/org.sonarqube
    id("com.adarshr.test-logger") version "4.0.0"//https://plugins.gradle.org/plugin/com.adarshr.test-logger
    id("com.github.ben-manes.versions") version "0.52.0" //https://plugins.gradle.org/plugin/com.github.ben-manes.versions
//	id("org.asciidoctor.jvm.convert") version "4.0.2" //https://asciidoctor.github.io/asciidoctor-gradle-plugin/master/user-guide/
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
    project.logger.debug("springVersion: ${libs.versions.springVersion.get()}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
    implementation("org.sonarsource.scanner.gradle", "sonarqube-gradle-plugin", version = "${libs.versions.sonarqubeGradle.get()}" )
    implementation("org.springframework.boot:spring-boot-gradle-plugin:${libs.versions.springBoot.get()}")
//    https://stackoverflow.com/questions/76540095/what-is-the-difference-of-using-implementation-platform-instead-of-depende
    implementation("io.spring.dependency-management:io.spring.dependency-management.gradle.plugin:${libs.versions.ioSpringDepManagement.get()}")

    implementation("com.github.ben-manes:gradle-versions-plugin:${libs.versions.benManesGradle.get()}")
    implementation("me.champeau.gradle:japicmp-gradle-plugin:${libs.versions.japicmp.get()}")
    implementation("org.owasp:dependency-check-gradle:${libs.versions.owaspcvecheck.get()}")

    testImplementation("org.springframework:spring-test:${libs.versions.springVersion.get()}")
    testImplementation("junit:junit:${libs.versions.junitVersion.get()}")
    testImplementation("org.hamcrest:hamcrest-all:${libs.versions.hamcrestVersion.get()}")
    testImplementation("org.easymock:easymock:${libs.versions.easymockVersion.get()}")
}