FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} helper-rest-service:0.0.1-SNAPSHOT.war
EXPOSE 80
VOLUME \logs
ENTRYPOINT ["java","-jar","/helper-rest-service:0.0.1-SNAPSHOT.war"]