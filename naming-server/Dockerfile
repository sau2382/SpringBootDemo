FROM openjdk:11
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} RestServiceDemo-0.0.1-SNAPSHOT.war
EXPOSE 80
VOLUME \logs
ENTRYPOINT ["java","-jar","/RestServiceDemo-0.0.1-SNAPSHOT.war"]