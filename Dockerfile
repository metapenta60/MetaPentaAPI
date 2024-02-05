# Stage 1: Build the application
FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /build
# Copy source code and pom.xml to the container
COPY src src
COPY pom.xml .
# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Package the application
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the built jar file from the build stage
COPY --from=build /build/target/metapenta-0.0.1-SNAPSHOT.jar app.jar
# Make port 8080 available outside the container
EXPOSE 8080
# Run the application
ENTRYPOINT ["java","-jar","app.jar"]