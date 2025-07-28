description = "Spring Differential Synchronization and JSON Patch"

plugins{
    id("jar-publishing")
    id("spring-conventions")
    id("testing-conventions")
    id("wgu-repository")
    id("java-conventions")

}

dependencyManagement{
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.springBoot.get()}")
    }
}

dependencies {
    api("org.springframework:spring-expression:${libs.versions.springVersion.get()}")
    api("commons-lang:commons-lang:${libs.versions.commonsLangVersion.get()}")
    api("com.googlecode.java-diff-utils:diffutils:${libs.versions.diffUtilsVersion.get()}")
//    api("org.slf4j:slf4j-api:${libs.versions.slf4jVersion.get()}")
    api("com.fasterxml.jackson.core:jackson-databind")

    testImplementation(enforcedPlatform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.platform.launcher)
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}