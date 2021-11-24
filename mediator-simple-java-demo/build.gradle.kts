@Suppress("DSL_SCOPE_VIOLATION") // TODO remove when https://youtrack.jetbrains.com/issue/KTIJ-19369 is fixed
plugins {
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependencyManagement)
	java
	alias(libs.plugins.lombok)
}

version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.spring.boot.web)
	implementation(libs.spring.boot.jpa)
	implementation(libs.spring.boot.actuator)
	
	runtimeOnly(libs.h2)
	testImplementation(libs.spring.boot.test)
}

tasks.withType<Test> {
	useJUnitPlatform()
}
