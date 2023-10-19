plugins {
	java
	id ("org.springframework.boot") version "3.1.3"
	id ("io.spring.dependency-management") version "1.1.3"
	checkstyle
}

group = "ucr.ac"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("org.liquibase:liquibase-core")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation ("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("com.h2database:h2")
	testImplementation("org.assertj:assertj-core:3.11.1")
	testImplementation ("com.tngtech.archunit:archunit-junit5:1.1.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}