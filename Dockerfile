# OpenJDK 기반 이미지 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 생성
WORKDIR /app

COPY build/libs/mypage-0.0.1-SNAPSHOT.jar app.jar

# 실행할 명령 설정
ENTRYPOINT ["java", "-jar", "app.jar"]

# 컨테이너가 사용하는 포트 노출
EXPOSE 8080
