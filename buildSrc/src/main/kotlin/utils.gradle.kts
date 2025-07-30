import org.gradle.api.tasks.Copy
plugins {
    id("com.github.ben-manes.versions")
}

tasks.register("allDependencies") {
    group = "verification"
    description = "run dependencies task for all subprojects"
}

val excludedProjects = listOf("")
allprojects {
    tasks.register("runDependencies")  {
        dependsOn("dependencies")
    }
    tasks.getByName("allDependencies").dependsOn("runDependencies")

    if(!pluginManager.hasPlugin("java-platform") && project.name !in excludedProjects) {
        // needed by Veracode to upload dependencies
        tasks.register<Copy>("copyDependencies") {
            dependsOn("jar")
            from(configurations.getByName("compileClasspath"))
            into("${layout.buildDirectory.get()}/libs/")
            include("wgu*.jar")
            doLast {
                println("Copied compile dependencies")
            }
        }

        tasks.named("check") {
            dependsOn("copyDependencies")
        }
    }
}