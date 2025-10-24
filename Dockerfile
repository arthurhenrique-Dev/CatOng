FROM maven:3.9.11-amazoncorretto AS build
COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:21

COPY --from=build /app/target/CatOng-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java","-jar", "app.jar"]