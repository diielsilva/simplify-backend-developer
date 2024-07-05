FROM maven:3-amazoncorretto-21 AS build
COPY /src /app/src
COPY /pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM amazoncorretto:21-alpine-jdk
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]