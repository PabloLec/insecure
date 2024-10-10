FROM maven:3.6.0-jdk-11-slim AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean package -DskipTests

FROM openjdk:24-ea-17-jdk-oraclelinux8

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/insecure-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "insecure-0.0.1-SNAPSHOT.jar"]