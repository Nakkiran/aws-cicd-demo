# -------- Stage 1: Build with Maven --------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first and resolve dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the app (you can enable tests by removing -DskipTests)
RUN mvn clean package -DskipTests

# -------- Stage 2: Run the app --------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Optional: Document the port the app uses
EXPOSE 8000

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar"]
