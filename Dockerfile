FROM openjdk:8-jdk-alpine as build

WORKDIR employee
COPY target/employee-0.0.1-SNAPSHOT.jar employee

EXPOSE 8080
CMD ["java", "-jar", "employee-0.0.1-SNAPSHOT.jar"]