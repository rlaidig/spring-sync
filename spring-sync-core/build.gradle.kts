description = "Spring Differential Synchronization and JSON Patch"
group = "org.springframework.sync"

plugins{
    id("jar-publishing")
    id("testing-conventions")
    id("wgu-repository")
    id("java-conventions")
    id("utils")
}

dependencies {
    api(platform("org.springframework.boot:spring-boot-dependencies:${libs.versions.springBoot.get()}"))
    api("org.springframework:spring-expression:${libs.versions.springVersion.get()}")
    api("org.apache.commons:commons-lang3:${libs.versions.commonsLangVersion.get()}")
    api("com.googlecode.java-diff-utils:diffutils:${libs.versions.diffUtilsVersion.get()}")
    api("com.fasterxml.jackson.core:jackson-databind")

    testImplementation(enforcedPlatform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.platform.launcher)
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}