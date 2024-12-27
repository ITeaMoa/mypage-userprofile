# Stage 1: Build the JAR file
FROM public.ecr.aws/docker/library/openjdk:17-jdk-slim AS builder

WORKDIR /app

# Pass build arguments and set environment variables
ARG AWS_DEFAULT_REGION
ARG AWS_DYNAMODB_TABLE
ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY
ARG AWS_S3_BUCKET_NAME

ENV AWS_DEFAULT_REGION=${AWS_DEFAULT_REGION}
ENV AWS_DYNAMODB_TABLE=${AWS_DYNAMODB_TABLE}
ENV AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
ENV AWS_S3_BUCKET_NAME=${AWS_S3_BUCKET_NAME}

# Copy Gradle wrapper and project files
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY src /app/src

# Grant execution permissions to Gradle wrapper
RUN chmod +x ./gradlew

# Build the application and echo the contents of the build/libs directory
RUN ./gradlew clean build

# Stage 2: Copy the JAR file and set up the runtime
FROM public.ecr.aws/docker/library/openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/mypage-0.0.1-SNAPSHOT.jar app.jar

# Echo to confirm the JAR file is in place
RUN echo "Contents of /app after copying JAR:" && ls -l /app

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
