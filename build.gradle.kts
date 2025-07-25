//buildscript {
//	repositories {
//		maven { url "https://repo.spring.io/plugins-release" }
//	}
//	dependencies {
//		classpath("org.springframework.build.gradle:propdeps-plugin:0.0.7")
//		classpath("org.springframework.build.gradle:spring-io-plugin:0.0.3.RELEASE")
//		classpath('org.asciidoctor:asciidoctor-gradle-plugin:0.7.0')
//		classpath('org.asciidoctor:asciidoctor-java-integration:0.1.4.preview.1')
//		classpath("me.champeau.gradle:gradle-javadoc-hotfix-plugin:0.1")
//	}
//}











//	if (project.hasProperty('platformVersion')) {
//		apply plugin: 'spring-io'
//
//		repositories {
//			maven { url "https://repo.spring.io/libs-snapshot" }
//		}
//
//		dependencies {
//			springIoVersions "io.spring.platform:platform-versions:${platformVersion}@properties"
//		}
//	}

//	compileJava {
//		sourceCompatibility=1.6
//		targetCompatibility=1.6
//	}
//	compileTestJava {
//		sourceCompatibility=1.7
//		targetCompatibility=1.7
//	}

//	[compileJava, compileTestJava]*.options*.compilerArgs = [
//		"-Xlint:serial",
//		"-Xlint:varargs",
//		"-Xlint:cast",
//		"-Xlint:classfile",
//		"-Xlint:dep-ann",
//		"-Xlint:divzero",
//		"-Xlint:empty",
//		"-Xlint:finally",
//		"-Xlint:overrides",
//		"-Xlint:path",
//		"-Xlint:processing",
//		"-Xlint:static",
//		"-Xlint:try",
//		"-Xlint:fallthrough",
//		"-Xlint:rawtypes",
//		"-Xlint:deprecation",
//		"-Xlint:unchecked",
//		"-Xlint:-options",
//		"-Werror"
//	]

//	sourceSets.test.resources.srcDirs = [
//		"src/test/resources",
//		"src/test/java"
//	]

//	tasks.withType(Test).all {
//		systemProperty("java.awt.headless", "true")
//	}

//	repositories {
//		maven { url "https://repo.spring.io/libs-snapshot" }
//	}





//
//	val javadocLinks = [
//		"http://docs.oracle.com/javase/8/docs/api/",
//		"http://docs.oracle.com/javaee/8/api/",
//		"http://docs.spring.io/spring/docs/${springVersion}/javadoc-api/"
//	] as String[]

//	// servlet-api (2.5) and tomcat-servlet-api (3.0) classpath entries should not be
//	// exported to dependent projects in Eclipse to avoid false compilation errors due
//	// to changing APIs across these versions
//	eclipse.classpath.file.whenMerged { classpath ->
//		classpath.entries.findAll { entry -> entry.path.contains("servlet-api") }*.exported = false
//	}

//configure(subprojects) { subproject ->
//	apply from: "${rootProject.projectDir}/publish-maven.gradle"
//
//	if (project.hasProperty('platformVersion')) {
//		apply plugin: 'spring-io'
//
//		repositories {
//			maven { url "https://repo.spring.io/libs-snapshot" }
//		}
//
//		dependencies {
//			springIoVersions "io.spring.platform:platform-versions:${platformVersion}@properties"
//		}
//	}
//
//	jar {
//		manifest.attributes["Created-By"] =
//				"${System.getProperty("java.version")} (${System.getProperty("java.specification.vendor")})"
//		manifest.attributes["Implementation-Title"] = subproject.name
//		manifest.attributes["Implementation-Version"] = subproject.version
//
//		from("${rootProject.projectDir}/src/dist") {
//			include "license.txt"
//			include "notice.txt"
//			into "META-INF"
//			expand(copyright: new Date().format("yyyy"), version: project.version)
//		}
//	}
//
//	javadoc {
//		description = "Generates project-level javadoc for use in -javadoc jar"
//
//		options.memberLevel = org.gradle.external.javadoc.JavadocMemberLevel.PROTECTED
//		options.author = true
//		options.header = project.name
//		options.links(project.ext.javadocLinks)
//		if (JavaVersion.current().isJava8Compatible()) {
//			options.addStringOption('Xdoclint:none', '-quiet')
//		}
//	}

//	task sourcesJar(type: Jar, dependsOn:classes) {
//		classifier = "sources"
//		from sourceSets.main.allJava
//	}
//
//	task javadocJar(type: Jar) {
//		classifier = "javadoc"
//		from javadoc
//	}
//
//	artifacts {
//		archives sourcesJar
//		archives javadocJar
//	}
//
//	configurations {
//		springReleaseTestRuntime.extendsFrom testRuntime
//		springSnapshotTestRuntime.extendsFrom testRuntime
//	}
//
//	// Test against latest Spring Framework release
//	configurations.springReleaseTestRuntime {
//		resolutionStrategy.eachDependency { DependencyResolveDetails details ->
//			if (details.requested.group == 'org.springframework') {
//				details.useVersion springReleaseVersion
//			}
//		}
//	}
//
//	// Test against latest Spring Framework snapshot
//	configurations.springSnapshotTestRuntime {
//		resolutionStrategy.eachDependency { DependencyResolveDetails details ->
//			if (details.requested.group == 'org.springframework') {
//				details.useVersion springSnapshotVersion
//			}
//		}
//	}
//
//	task springReleaseTest(type: Test) {
//		classpath = sourceSets.test.output + sourceSets.main.output + configurations.springReleaseTestRuntime
//		getReports().getJunitXml().setDestination(file("$buildDir/spring-release-test-results/"))
//		getReports().getHtml().setDestination(file("$buildDir/reports/spring-release-tests/"))
//	}
//	check.dependsOn springReleaseTest
//
//	task springSnapshotTest(type: Test) {
//		classpath = sourceSets.test.output + sourceSets.main.output + configurations.springSnapshotTestRuntime
//		getReports().getJunitXml().setDestination(file("$buildDir/spring-snapshot-test-results/"))
//		getReports().getHtml().setDestination(file("$buildDir/reports/spring-snapshot-tests/"))
//	}
//	check.dependsOn springSnapshotTest
//
//	configure([test, springReleaseTest, springSnapshotTest]) {
//		systemProperties['springVersion'] = springVersion
//	}

//tasks.test {
//	useJUnitPlatform()
//	testLogging {
//		events("passed", "skipped", "failed")
//	}
//
//	finalizedBy(tasks.jacocoTestReport)
//}

//tasks.jacocoTestReport {
//	dependsOn(tasks.test)
//	reports {
//		xml.required.set(true)
//		csv.required.set(true)
//		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
//	}
//}

//tasks.withType<JavaCompile>() {
//	options.compilerArgs.add("-parameters")
//}

//jacoco {
//	toolVersion = "0.8.13" // https://docs.gradle.org/current/userguide/jacoco_plugin.html https://mvnrepository.com/artifact/org.jacoco/org.jacoco.build
//	reportsDirectory.set(layout.buildDirectory.dir("jacocoReports"))
//}
//sonar {
//	properties {
//		property("sonar.projectKey", "WGU-edu_ngp-kernel-idmap-sdk")
//		property("sonar.organization", "wgu-edu")
//		property("sonar.host.url", "https://sonarcloud.io")
//		property("sonar.exclusions", "**/*Properties*,**/InternalBannerProxyConfig*,**/IdMapStaffStorage*,**IdMapClientService*")
//	}
//}





//project('spring-sync-core-android') {
//	description = "Spring Differential Synchronization and JSON Patch for Android"

//	// creates a separate artifact for Android which builds against different dependencies
//
//	sourceSets {
//		main {
//			java {
//				srcDir '../spring-sync-core/src/main/java'
//				exclude 'org/springframework/sync/diffsync/shadowstore/Redis**'
//				exclude 'org/springframework/sync/diffsync/shadowstore/Gem**'
//			}
//		}
//	}

//	dependencies {
//		implementation("org.springframework.android:spring-android-core:${springAndroidVersion}")
//		implementation("org.springframework:spring-expression:3.2.11.RELEASE") {
//			// exclude in favor of Spring for Android Core
//			exclude( group: "org.springframework", module: "spring-core")
//		}
//		implementation("commons-lang:commons-lang:${commonsLangVersion}")
//		implementation("com.googlecode.java-diff-utils:diffutils:${diffUtilsVersion}")
//		optional("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
//	}
//}

//project('spring-sync-web') {
//	description = "Spring Differential Synchronization and JSON Patch"
//	dependencies {
//		compile(project(":spring-sync-core"))
//		compile("org.springframework:spring-webmvc:${springVersion}")
//		optional("javax.servlet:javax.servlet-api:${servletApiVersion}")
//		optional("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
//		testCompile("org.hibernate:hibernate-entitymanager:4.3.5.Final")
//		testCompile("com.h2database:h2:1.4.180")
//		testCompile("org.springframework.data:spring-data-commons:${springDataCommonsVersion}")
//		testCompile("org.springframework.data:spring-data-jpa:${springDataJpaVersion}")
//		testCompile("org.springframework:spring-orm:${springVersion}")
//		testCompile("com.jayway.jsonpath:json-path:${jsonPathVersion}")
//		testCompile("com.jayway.jsonpath:json-path-assert:${jsonPathVersion}")
//	}
//}

//configure(rootProject) {
//	description = "Spring Sync"
//
//	// don't publish the default jar for the root project
//	configurations.archives.artifacts.clear()
//
//	dependencies {
//		// for integration tests
//	}
//
//	task api(type: Javadoc) {
//		group = "Documentation"
//		description = "Generates aggregated Javadoc API documentation."
//		title = "${rootProject.description} ${version} API"
//		options.memberLevel = org.gradle.external.javadoc.JavadocMemberLevel.PROTECTED
//		options.author = true
//		options.header = rootProject.description
//		options.overview = "src/api/overview.html"
//		options.links(project.ext.javadocLinks)
//		if (JavaVersion.current().isJava8Compatible()) {
//			options.addStringOption('Xdoclint:none', '-quiet')
//		}
//
//		source subprojects.collect { project ->
//			project.sourceSets.main.allJava
//		}
//
//		classpath = files(subprojects.collect { project ->
//			project.sourceSets.main.compileClasspath
//		})
//
//		maxMemory = "1024m"
//		destinationDir = new File(buildDir, "api")
//	}
//
//// Task for creating the distro zip
//
//task dist(type: Zip) {
//	dependsOn subprojects*.tasks*.matching { task -> task.name == 'assemble' || task.name.endsWith('Zip') || task.name.endsWith('generatePom') }
//	classifier = 'dist'
//
//	evaluationDependsOn(':docs')
//	evaluationDependsOn(':docs:manual')
//
//	def zipRootDir = "${project.name}-$version"
//	into(zipRootDir) {
//		from(rootDir) {
//			include '*.txt'
//		}
//		into('docs') {
//			with(project(':docs').apiSpec)
//			with(project(':docs:manual').spec)
//		}
//		into('dist') {
//			from coreModuleProjects.collect {project -> project.libsDir }
//		}
//	}
//}
//
//artifacts {
//	archives dist
//	archives project(':docs').docsZip
//}
//
//	task wrapper(type: Wrapper) {
//		gradleVersion = "1.12"
//	}
//
//}
