FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

ENV Database_Host
ENV Database_Username
ENV Database_Password
ENV Bearer_API_Key
ENV Rapid_API_Key
ENV Open_AI_Key

EXPOSE 8080

CMD ["java", "-jar", "target/CarbonWise-0.0.1-SNAPSHOT.jar"]