##  Start with a base image from Eclipse Temurin (a distribution of OpenJDK) with Alpine 
FROM eclipse-temurin:21-jammy as build
WORKDIR /workspace/app


## Copy neccessary files for building the java project
COPY gradlew .
RUN chmod +x ./gradlew
COPY gradle/wrapper/gradle-wrapper.properties ./gradle/wrapper/gradle-wrapper.properties
COPY gradle/wrapper/gradle-wrapper.jar ./gradle/wrapper/gradle-wrapper.jar

COPY config/checkstyle/checkstyle.xml ./config/checkstyle/checkstyle.xml

COPY build.gradle .

COPY src src

## Ensure the Gradle Wrapper script is executable
RUN chmod +x ./gradlew

## Use Gradle to build the application using the RUN stanza
RUN ./gradlew build

## need to add a command to run the app here
ENTRYPOINT ["java","-jar","build/libs/notes-app-1.0.jar"]
CMD [ "bash", "-c", "java -jar build/libs/notes-app-1.0.jar & tail -f /dev/null"]

## have this here because I was debugging the jar file
## Add a command to run a shell in interactive mode when the container starts
#CMD ["/bin/sh"]