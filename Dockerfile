# ==============================
# 🔧 Stage 1: Build the application
# ==============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the JAR file (skip tests for faster build)
RUN mvn clean package -DskipTests


# ==============================
# 🚀 Stage 2: Run the application
# ==============================
FROM eclipse-temurin:17-jre-alpine

# Set working directory inside runtime container
WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

