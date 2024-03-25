FROM maven:3.8.1-openjdk-17-slim AS build

RUN mkdir /project

COPY . /project

WORKDIR /project

RUN mvn clean package

FROM openjdk:17-alpine

RUN mkdir /app

COPY --from=build /project/target/*.jar /app/link-generator.jar

COPY --from=build /project/src/main/resources/application.properties /app/config.properties

WORKDIR /app

EXPOSE 8099

ENTRYPOINT ["java", "-Dspring.config.location=/app/config.properties", "-jar", "/app/link-generator.jar"]