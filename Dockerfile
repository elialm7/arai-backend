# ======== STAGE 1: Build ========
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory inside build container
WORKDIR /app

# Copy pom.xml and download dependencies first (cache optimization)
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# ======== STAGE 2: Runtime ========
FROM eclipse-temurin:17-jdk-jammy

# Set working directory inside runtime container
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/arai-backend-0.0.1-SNAPSHOT.jar app.jar

# Create log folder and expose port
RUN mkdir -p /app/logs
EXPOSE 5002

# Optional volumes for logs and external config
VOLUME ["/app/logs", "/app/.env.properties"]

# Entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]

