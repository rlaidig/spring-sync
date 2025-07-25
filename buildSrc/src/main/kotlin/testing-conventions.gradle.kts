plugins {
    id("java-conventions")
    jacoco
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events("PASSED", "SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val jacocoVersion = versionCatalog.findVersion("jacoco").get().toString()

jacoco {
    toolVersion = jacocoVersion
    reportsDirectory.set(layout.buildDirectory.dir("reports/jacoco/test/jacocoTestReport.xml"))
}