FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/studycase-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]