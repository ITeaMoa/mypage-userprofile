# Use an official OpenJDK runtime (Java 17) as the base image for running the Spring Boot app
# FROM openjdk:17-jdk-slim - 429 Too Many Requests error 때문에 pull resource ecr로 바뀜
FROM public.ecr.aws/docker/library/openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

ARG AWS_DEFAULT_REGION
ARG AWS_TABLE
ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY

# # Set environment variables
# ENV AWS_DEFAULT_REGION=${AWS_DEFAULT_REGION}
# ENV AWS_TABLE=${AWS_TABLE}
# ENV AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
# ENV AWS_SECRET_KEY=${AWS_SECRET_KEY}

# Copy the Gradle build files to the container
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY src /app/src

# Grant execution rights to the Gradle wrapper
RUN chmod +x ./gradlew

# Build the application JAR using Gradle
RUN ./gradlew build

# 컨테이너가 사용하는 포트 노출
EXPOSE 8080

COPY build/libs/mypage-0.0.1-SNAPSHOT.jar /app/mypage.jar
COPY build/libs/mainpage-0.0.1-SNAPSHOT.jar /app/mainpage.jar

# 실행할 명령 설정
ENTRYPOINT ["java", "-jar", "app.jar"]