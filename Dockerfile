FROM maven:3.9.0-openjdk-17 AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM selenium/standalone-chrome:latest

USER root
RUN apt-get update && apt-get install -y openjdk-17-jdk allure \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=builder /app/target /app/target
CMD ["mvn", "clean", "test"]
