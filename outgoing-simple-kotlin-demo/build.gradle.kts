import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // TODO remove when https://youtrack.jetbrains.com/issue/KTIJ-19369 is fixed
plugins {
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependencyManagement)
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.kotlin.jpa)
}

version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.spring.boot.web)
	implementation(libs.bundles.spring.boot.webflux)
	implementation(libs.spring.boot.jpa)
	implementation(libs.spring.boot.actuator)
	implementation(libs.jackson.kotlin)
	implementation(libs.kotlin.reflect)
	implementation(libs.resilience4j)
	implementation(libs.chaosMonkey)
	runtimeOnly(libs.h2)
	testImplementation(libs.spring.boot.test)
	testImplementation(libs.project.reactor.test)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
