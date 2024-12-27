# Use an official OpenJDK runtime (Java 17) as the base image for running the Spring Boot app
# FROM openjdk:17-jdk-slim - 429 Too Many Requests error 때문에 pull resource ecr로 바뀜
FROM public.ecr.aws/docker/library/openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

ARG AWS_DEFAULT_REGION
ARG AWS_DYNAMODB_TABLE
ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY
ARG AWS_S3_BUCKET_NAME


# Copy the Gradle build files to the container
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY src /app/src

# Grant execution rights to the Gradle wrapper
RUN chmod +x ./gradlew

# Build the application JAR using Gradle
RUN ./gradlew clean build && ls -l build/libs

RUN ls build/libs/mypage-0.0.1-SNAPSHOT.jar

# 애플리케이션 jar 파일을 컨테이너로 복사
COPY build/libs/mypage-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# 애플리케이션 실행 명령어 설정
ENTRYPOINT ["java", "-jar", "app.jar"]

