description = "Spring Differential Synchronization and JSON Patch"
dependencies {
    api(project(":spring-sync-core"))
    implementation("org.springframework:spring-webmvc:${springVersion}")
    runtimeOnly("javax.servlet:javax.servlet-api:${servletApiVersion}")
    runtimeOnly("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    testimplementation("org.hibernate:hibernate-entitymanager:4.3.5.Final")
    testimplementation("com.h2database:h2:1.4.180")
    testimplementation("org.springframework.data:spring-data-commons:${springDataCommonsVersion}")
    testimplementation("org.springframework.data:spring-data-jpa:${springDataJpaVersion}")
    testimplementation("org.springframework:spring-orm:${springVersion}")
    testimplementation("com.jayway.jsonpath:json-path:${jsonPathVersion}")
    testimplementation("com.jayway.jsonpath:json-path-assert:${jsonPathVersion}")
}
