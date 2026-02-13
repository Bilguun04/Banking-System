# Stage 1: Build with Gradle
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /build

# Install Gradle
RUN apk add --no-cache gradle

# Copy build files first for better layer caching
COPY build.gradle settings.gradle gradle.properties ./
COPY src ./src

# Build the application
RUN gradle clean bootJar -x test --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring

# Copy built jar from builder
COPY --from=builder /build/build/libs/*.jar app.jar

# Change ownership
RUN chown spring:spring app.jar

USER spring:spring

EXPOSE 8080

# Run with container-aware JVM settings
ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-jar", \
    "app.jar"]