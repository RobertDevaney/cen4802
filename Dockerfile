FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/cen4802-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-cp", "app.jar", "com.cen4802.FibonacciApp", "server"]