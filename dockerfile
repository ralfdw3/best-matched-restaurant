# build stage
FROM gradle:8.14.0-jdk21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon

# runtime stage
FROM openjdk:21-jdk-slim
COPY --from=builder /app/build/libs/*.jar /app.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
EXPOSE 8080