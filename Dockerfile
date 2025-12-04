# Use a Maven image with JDK 11 to build the app
FROM maven:3.8.7-eclipse-temurin-11 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (cache optimization)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy full project and build it
COPY . .
RUN mvn clean package -DskipTests

# ---- RUNTIME IMAGE ----
FROM eclipse-temurin:11-jre
WORKDIR /app

# Copy built JAR from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
