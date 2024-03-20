
FROM maven:3.8.5-openjdk-17 AS build

ARG PORT
ENV PORT=${PORT}
ARG bearerToken
ENV bearerToken=${bearerToken}

COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/BestMovie-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]



