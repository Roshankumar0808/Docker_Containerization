FROM maven:3.9.4-eclipse-temurin-21-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src

EXPOSE 8080

CMD ["java", "-jar", "product-serivce-0.0.1-SNAPSHOT.jar"]