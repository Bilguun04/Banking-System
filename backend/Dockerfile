FROM openjdk:17-slim as builder
WORKDIR /build
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts gradle.properties gradlew ./
COPY src ./src
RUN chmod +x ./gradlew && \
    ./gradlew clean bootJar -x test

FROM openjdk:17-slim
WORKDIR /app

# Copy built jar from builder
COPY --from=builder /build/build/libs/banking-system-backend-1.0.0.jar app.jar

# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

# Run with debug logging and proper encoding
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
