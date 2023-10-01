FROM openjdk:17
ARG JAR_FILE=target/ApiConsolidator-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ADD ${JAR_FILE} ApiConsolidator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/ApiConsolidator-0.0.1-SNAPSHOT.jar"]
