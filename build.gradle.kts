import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "dev.mainconde"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_13

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("mysql:mysql-connector-java")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt
	implementation ("io.jsonwebtoken:jjwt:0.9.1")

	// https://mvnrepository.com/artifact/com.google.code.gson/gson
	implementation ("com.google.code.gson:gson:2.10")

	// https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
	compileOnly("javax.servlet:javax.servlet-api:4.0.1")

	//Mygration
	implementation("org.flywaydb:flyway-core:7.7.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "13"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
