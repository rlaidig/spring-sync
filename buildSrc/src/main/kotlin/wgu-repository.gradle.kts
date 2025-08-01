import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.repositories
import java.net.URI;

plugins {
    `maven-publish`
}

val isPublishing: Boolean by project
val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val mavenUser: String by project
val mavenPassword: String by project

val releaseRepo = "release-repo"
val snapshotRepo = "snapshot-repo"

val projectVersion = "${versionCatalog.findVersion("org_springframework_sync-version").get()}"



allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven {
            name = releaseRepo
            url = URI("https://nexus.wgu.edu/content/repositories/WGU")
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
            url = URI("https://nexus.wgu.edu/content/repositories/WGUSnapshots")
            credentials {
                username = mavenUser
                password = mavenPassword
            }
            mavenContent {
                snapshotsOnly()
            }
        }
    }
    publishing {
        repositories {
            val isSnapshot = projectVersion.endsWith("-SNAPSHOT")
            val isRelease = !isSnapshot;
            println("${project.name} Version: ${projectVersion}, isRelease: ${isRelease}, isSnapshot: ${isSnapshot}")

            if (isRelease) {
                maven {
                    name = releaseRepo
                    url = URI("https://nexus.wgu.edu/content/repositories/WGU")
                    credentials {
                        username = mavenUser
                        password = mavenPassword
                    }
                    mavenContent {
                        releasesOnly()
                    }
                }
            } else if (isSnapshot) {
                maven {
                    name = snapshotRepo
                    url = URI("https://nexus.wgu.edu/content/repositories/WGUSnapshots")
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
    }
}