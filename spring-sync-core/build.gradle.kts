description = "Spring Differential Synchronization and JSON Patch"

plugins{
    id("jar-publishing")
}
project.logger.debug("springVersion: ${libs.versions.springVersion.get()}")
dependencies {
    api("org.springframework:spring-core:${libs.versions.springVersion.get()}")
    api("org.springframework:spring-expression:${libs.versions.springVersion.get()}")
    api("commons-lang:commons-lang:${libs.versions.commonsLangVersion.get()}")
    api("com.googlecode.java-diff-utils:diffutils:${libs.versions.diffUtilsVersion.get()}")
    api("org.slf4j:slf4j-api:${libs.versions.slf4jVersion.get()}")
    runtimeOnly("org.springframework.data:spring-data-redis:${libs.versions.springDataRedisVersion.get()}")
    runtimeOnly("org.springframework.data:spring-data-gemfire:${libs.versions.springDataGemfireVersion.get()}")
    runtimeOnly("com.fasterxml.jackson.core:jackson-databind:${libs.versions.jacksonVersion.get()}")
    testImplementation("com.jayway.jsonpath:json-path:${libs.versions.jsonPathVersion.get()}")
    testImplementation("com.jayway.jsonpath:json-path-assert:${libs.versions.jsonPathVersion.get()}")
}