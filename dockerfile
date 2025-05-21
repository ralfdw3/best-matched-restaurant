FROM openjdk:21-jdk-slim
COPY build/libs/*.jar /app.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]
EXPOSE 8080