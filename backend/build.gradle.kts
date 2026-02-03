import org.gradle.api.tasks.testing.logging.TestLoggingContainer

plugins {
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.banking"
version = "1.0.0"
description = "Spring Boot backend for Banking System"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Spring Boot Data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Spring Boot Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // H2 Database (for development)
    runtimeOnly("com.h2database:h2")

    // MySQL Driver (for production)
    implementation("com.mysql:mysql-connector-java:8.0.33")

    // Spring Boot Data Redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // Jedis Client for Redis
    implementation("redis.clients:jedis")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Test Dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

tasks.bootJar {
    archiveFileName.set("${archiveBaseName.get()}-${archiveVersion.get()}.jar")
}
