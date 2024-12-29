# Stage 1: Build the JAR file
FROM public.ecr.aws/docker/library/openjdk:17-jdk-slim

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

RUN echo ${PWD} && ls -lR

COPY build/libs/mypage-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080
