// needed for using 'libs' version catalog as libs.versions... in buildSrc/build.gradle.kts
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}