FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

ARG Database_Host
ARG Database_Username
ARG Database_Password
ARG Bearer_API_Key
ARG Rapid_API_Key
ARG Open_AI_Key

ENV Database_Host=$Database_Host
ENV Database_Username=$Database_Username
ENV Database_Password=$Database_Password
ENV Bearer_API_Key=$Bearer_API_Key
ENV Rapid_API_Key=$Rapid_API_Key
ENV Open_AI_Key=$Open_AI_Key

EXPOSE 8080

CMD ["java", "-jar", "target/CarbonWise-0.0.1-SNAPSHOT.jar"]