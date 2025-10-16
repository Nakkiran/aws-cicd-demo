# Use the official OpenJDK 17 image from Amazon ECR Public (avoids Docker Hub rate limit)
FROM public.ecr.aws/docker/library/openjdk:17

# Set working directory inside the container
WORKDIR /app

# Copy the compiled Spring Boot JAR into the container
COPY ./target/aws-cicd-demo.jar /app

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "aws-cicd-demo.jar"]
