description = "Spring Sync Configuration"
dependencies {
    api(project(":spring-sync-core"))
    api(project(":spring-sync-web"))
    implementation("org.springframework:spring-core:${springVersion}")
    implementation("org.springframework:spring-context:${springVersion}")
    runtimeOnly("javax.servlet:javax.servlet-api:${servletApiVersion}")
}
