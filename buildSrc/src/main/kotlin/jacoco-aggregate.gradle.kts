plugins {
    id("org.sonarqube")
    id("jacoco-report-aggregation")
}

reporting {
    reports {
        val testCodeCoverageReport by creating(JacocoCoverageReport::class) {
            testSuiteName = "test"
        }
    }
}

sonar {
    properties {
        properties(
            hashMapOf<String, String>(
                "sonar.projectKey" to "", //todo create sonar project key
                "sonar.organization" to "wgu-edu",
                "sonar.host.url" to "https://sonarcloud.io",
                "sonar.core.codeCoveragePlugin" to "java",
                "sonar.sources" to "src/main/java",
                "sonar.exclusions" to "**/*Properties*,**/*Customizer*",
                "sonar.working.directory" to "${rootDir}/.jacocoAggregate/sonar", // separate from the default build directory which has our build.sh & deploy.sh and avoid from cleaning out
                "sonar.gradle.skipCompile" to "true",
            )
        )
    }
}


val skipProjects = setOf(
    ":docs",
    ":spring-sync-core-android-test",
    ":spring-sync-web",
    ":spring-sync-config"
)
skipProjects.forEach {
    project(it) {
        sonar {
            isSkipProject = true
        }
    }
}