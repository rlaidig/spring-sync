

plugins {
//    id("wgu-boot.spring-conventions")
    id("testing-conventions")
//    id("wgu-boot.dependency-overrides")
    id("utils")
    id("wgu-repository")
}

//val bootJar: BootJar by tasks
//bootJar.enabled = false

tasks.getByName<Jar>("jar") {
    enabled = true
    archiveClassifier.set("")
    manifest {
        attributes["Created-By"] = "Java ${JavaVersion.current()}"
    }
}
allprojects {
    publishing {
        publications.create<MavenPublication>("mavenJar") {
            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(project.name)
                url.set("https://ngp-kernel-wgu-boot3.wgu-edu.github.io/")
                developers {
                    developer {
                        id.set("kernel")
                        name.set("Kernel Team")
                    }
                }
                scm {
                    url.set("https://github.com/WGU-edu/ngp-kernel-wgu-boot3") //todo: update to the correct repo
                }
            }
        }
    }
}