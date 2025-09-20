
# 2. Use JDK image to ruthe jar
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy JAR file
COPY target/ChatRoomApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

